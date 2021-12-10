/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderStatusDAO;
import dal.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

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
        //view
        String accountView = request.getParameter("viewAccount");
        String orderView = request.getParameter("viewOrder");
        //get account from session
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("currentLogin");
        //edit status
        String editStatus = request.getParameter("editStatus");
        String getOrderId = request.getParameter("orderId");
        //check login
        int orderId = 0;
        int statusId = 0;
        try {
            orderId = Integer.parseInt(getOrderId);
        } catch (NumberFormatException e) {
        }
        try {
            statusId = Integer.parseInt(editStatus);
        } catch (NumberFormatException e) {
        }

        OrdersDAO odb = new OrdersDAO();
        OrderStatusDAO osbd = new OrderStatusDAO();

        List<Orders> listOrders = odb.getAllOrders(account.getId());
        List<OrderDetail> listOrDetails = (List<OrderDetail>) request.getAttribute("listOrDetails");
        //view and edit order status
        int roleUserId = 2;
        List<OrderStatus> listOrderStatus = osbd.getListStatusByRoleId(roleUserId);
        boolean checkEditStatus = false;
        if (statusId != 0 && orderId != 0) {
            checkEditStatus = odb.editOrderStatus(orderId, statusId);
            List<Orders> listOrder = odb.getAllOrders(account.getId());
            request.setAttribute("orderView", orderView);
            request.setAttribute("listOrders", listOrder);
        } else if (orderView != null && accountView == null) {
            request.setAttribute("listOrders", listOrders);
            request.setAttribute("listStatus", listOrderStatus);
            request.setAttribute("orderView", orderView);
        } else if (orderView != null && listOrDetails != null) {
            request.setAttribute("listOrDetails", listOrDetails);
            request.setAttribute("orderView", orderView);
        } else {
            request.setAttribute("accountView", accountView);
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
