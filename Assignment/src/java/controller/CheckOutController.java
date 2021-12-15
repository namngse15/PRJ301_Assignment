/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDetailDAO;
import dal.OrdersDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.OrderDetail;
import model.Orders;
import model.Product;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/payment"})
public class CheckOutController extends HttpServlet {

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
        OrdersDAO odb = new OrdersDAO();
        ProductDAO pdb = new ProductDAO();
        OrderDetailDAO odd = new OrderDetailDAO();

        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String total = request.getParameter("totalPrice");
        String note = request.getParameter("note");

        int totalPrice = 0;

        try {
            totalPrice = Integer.parseInt(total);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession(true);
        List<Cart> listCarts = (List<Cart>) session.getAttribute("listCarts");
        Account currentLogin = (Account) session.getAttribute("currentLogin");
        Orders order = new Orders(currentLogin.getId(), address, phone, currentLogin.getEmail(), totalPrice, 1, note);

        int orderId = odb.saveOrder(order);
        if (orderId != 0) {
            for (Cart cart : listCarts) {
                Product product = pdb.getOneProduct(cart.getProductId());
                int quantityInStock = product.getQuantity();
                int quantityCart = cart.getQuantity();

                if (quantityInStock > 0 && quantityCart < quantityInStock) {
                    OrderDetail orderDetail = new OrderDetail(product.getName(), cart.getColor(), cart.getQuantity(), product.getPrice(), cart.getProductId(), orderId);
                    boolean checkOdetail = odd.addOrderDetail(orderDetail);
                    int quantityAfter = quantityInStock - quantityCart;
                    pdb.updateQuantity(quantityAfter,product.getId());


                    if (checkOdetail == true) {
                        request.setAttribute("checkOdetail", checkOdetail);
                    }
                }
            }
            session.removeAttribute("listCarts");
            request.getRequestDispatcher("view-empty-cart").forward(request, response);
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
