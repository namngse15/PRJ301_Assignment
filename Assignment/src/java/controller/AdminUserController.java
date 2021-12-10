/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.OrderDetailDAO;
import dal.OrderStatusDAO;
import dal.OrdersDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.OrderDetail;
import model.OrderStatus;
import model.Orders;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "AdminUserController", urlPatterns = {"/admin-user"})
public class AdminUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        Account adminAccount = (Account) session.getAttribute("adminLogin");

        // get account
        String accountView = request.getParameter("viewAccount");
        String getId = request.getParameter("account");
        //remove account
        String getAccRemoveId = request.getParameter("removeAccount");
        //get orders
        String getOrderId = request.getParameter("orderId");
        String getTotal = request.getParameter("total");
        String orderView = request.getParameter("viewOrder");
        //remove orders
        String getOrdRemoveId = request.getParameter("removeOrder");
        String getStatusToRemove = request.getParameter("statusId");
        //editStatus
        String editStatus = request.getParameter("editStatus");

        int statusId = 0;
        int accountId = 0;
        int accRemoveId = 0;
        int ordRemoveId = 0;
        int ordReStatus = 0;
        int orderId = 0;
        int total = 0;

        try {
            accountId = Integer.parseInt(getId);
        } catch (NumberFormatException e) {
        }
        try {
            total = Integer.parseInt(getTotal);
        } catch (NumberFormatException e) {
        }
        try {
            orderId = Integer.parseInt(getOrderId);
        } catch (NumberFormatException e) {
        }
        try {
            accRemoveId = Integer.parseInt(getAccRemoveId);
        } catch (NumberFormatException e) {
        }
        try {
            ordRemoveId = Integer.parseInt(getOrdRemoveId);
        } catch (NumberFormatException e) {
        }
        try {
            ordReStatus = Integer.parseInt(getStatusToRemove);
        } catch (NumberFormatException e) {
        }
        try {
            statusId = Integer.parseInt(editStatus);
        } catch (NumberFormatException e) {
        }

        AccountDAO adb = new AccountDAO();
        OrdersDAO odb = new OrdersDAO();
        ProductDAO pdb = new ProductDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        OrderStatusDAO osbd = new OrderStatusDAO();

        //getlocale date
        ZoneId zid = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate lt = LocalDate.now(zid);
        String localdate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(lt);
        //order
        int countOrder = odb.getCountTotalOrder();
        List<Orders> listOrders = odb.getAllUserOrders();
        List<Orders> listOrdersUser = odb.getAllOrders(accountId);
        List<OrderDetail> listOrDetails = odd.getOrderDetailByOrderID(orderId);
        //view and edit order status
        int roleAdminId = 1;
        List<OrderStatus> listOrderStatus = osbd.getListStatusByRoleId(roleAdminId);
        boolean checkEditStatus = false;
        if (statusId != 0 && orderId != 0) {
            checkEditStatus = odb.editOrderStatus(orderId, statusId);
            List<Orders> listOrder = odb.getAllUserOrders();
            request.setAttribute("listOrders", listOrder);
        }
        //account
        int roleUserId = 2;
        int count = adb.getCountTotalUser(roleUserId);
        List<Account> listUsers = adb.getListUserAccount(roleUserId);
        Account account = adb.getAccountById(accountId);
        //remove account
        boolean checkRemoveUser = false;
        if (accRemoveId != 0) {
            checkRemoveUser = adb.deleteAccount(accRemoveId);
        }
        //remove order
        boolean checkRemoveOrders = false;
        if (ordRemoveId != 0) {
            if (ordReStatus == 3 || ordReStatus == 7) {
                int quantity = 0;
                boolean checkUpQuantity = false;
                List<OrderDetail> list = odd.getProductIDandQuantityByOrderID(ordRemoveId);
                for (OrderDetail o : list) {
                    int quantityItem = pdb.getQuantityByProductId(o.getProductId());
                    System.out.println(quantityItem);
                    quantity = quantityItem + o.getProductQuantity();
                    checkUpQuantity = pdb.updateQuantity(quantity, o.getProductId());
                }
                if (checkUpQuantity) {
                    boolean removeorderDetail = odd.deleteOrderDetail(ordRemoveId);
                    if (removeorderDetail) {
                        checkRemoveOrders = odb.deleteOrders(ordRemoveId);
                    }
                }
            } else if (ordReStatus == 6) {
                boolean removeorderDetail = odd.deleteOrderDetail(ordRemoveId);
                if (removeorderDetail) {
                    checkRemoveOrders = odb.deleteOrders(ordRemoveId);
                }
            }
        }
        //upload list orders
        if (statusId == 0 && orderId == 0) {
            request.setAttribute("listOrders", listOrders);
        }
        request.setAttribute("localDate", localdate);
        request.setAttribute("listStatus", listOrderStatus);
        request.setAttribute("listOrdersUser", listOrdersUser);
        request.setAttribute("listUsers", listUsers);
        request.setAttribute("countusers", count);

        //set attribute for orders
        if (orderView != null) {
            request.setAttribute("orderId", orderId);
            request.setAttribute("orderView", orderView);
            request.setAttribute("countOrders", countOrder);
            request.setAttribute("listOrDetails", listOrDetails);
            request.setAttribute("total", total);
        }
        //set attribute for account
        if (accountView != null) {
            request.setAttribute("accountView", accountView);
            request.setAttribute("accountId", accountId);
            request.setAttribute("account", account);
        }
        if (adminAccount != null) {
            if (checkRemoveUser || checkRemoveOrders) {
                request.getRequestDispatcher("admin-user").forward(request, response);
            } else {
                request.getRequestDispatcher("adminUser.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("home");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
