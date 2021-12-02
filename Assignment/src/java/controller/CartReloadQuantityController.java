/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "CartReloadQuantityController", urlPatterns = {"/reloadquantity"})
public class CartReloadQuantityController extends HttpServlet {

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

        String productId = request.getParameter("productId");
        String color = request.getParameter("color");
        String getQuantity = request.getParameter("quantity");
        String reloadQtyBtn = request.getParameter("subQty");

        int quantity = 0;
        int totalPrice = 0;

        try {
            quantity = Integer.parseInt(getQuantity);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession(true);
        List<Cart> listCarts = (List<Cart>) session.getAttribute("listCarts");
        //quantity cart plus
        if (reloadQtyBtn.equals("plus")) {
            for (Cart cart : listCarts) {
                if (cart.getProductId().equals(productId) && cart.getColor().equals(color)) {
                    cart.setQuantity(cart.getQuantity() + 1);
                }
            }
            for (Cart cart : listCarts) {
                totalPrice += cart.getQuantity() * cart.getPrice();
            }
        //quantity cart minus    
        } else if (reloadQtyBtn.equals("minus")) {
            for (Cart cart : listCarts) {
                if (cart.getProductId().equals(productId) && cart.getColor().equals(color)) {
                    cart.setQuantity(cart.getQuantity() - 1);
                }
            }
            for (Cart cart : listCarts) {
                totalPrice += cart.getQuantity() * cart.getPrice();
            }
        }

        session.setAttribute("totalPrice", totalPrice);
        response.sendRedirect("cart.jsp");

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
