/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Product;
import model.ProductImg;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "CartAddController", urlPatterns = {"/add-to-cart"})
public class CartAddController extends HttpServlet {

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

        ProductDAO pdb = new ProductDAO();
        HttpSession session = request.getSession();

        String productId = request.getParameter("productId");
        String getColor = request.getParameter("color");
        String getQuantity = request.getParameter("quantity");
        String getEdit = request.getParameter("edit");
        String getColorEdit = request.getParameter("colorEdit");
        Product product = pdb.getOneProduct(productId);

        int productQuantity = 0;
        int quantity = 0;

        if (getQuantity == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(getQuantity);
        }

        List<Cart> listCarts = (List<Cart>) session.getAttribute("listCarts");
        List<ProductImg> listImage = pdb.getListImageByPid(productId);
        Product p = pdb.getOneProduct(productId);

        Cart cart = new Cart(productId, product.getName(),
                product.getPrice(), quantity, getColor, listImage);

        boolean checkExist = false;
        //no cart => add
        if (listCarts == null) {
            listCarts = new ArrayList<>();
            listCarts.add(cart);
            session.setAttribute("product", p);
            session.setAttribute("listCarts", listCarts);
        } else {
            for (Cart c : listCarts) {
                //check edit color or not
                if (getEdit != null && getColorEdit != null) {
                    if (c.getProductId().equals(productId) && c.getColor().equals(getColorEdit) && getEdit.equals("1")) {
                        c.setColor(getColor);
                        c.setQuantity(c.getQuantity());
                        checkExist = true;
                    }
                } else {
                    //cart have same productId and color => plus quantity
                    if (c.getProductId().equals(productId) && c.getColor().equals(getColor)) {
                        if (quantity == 1) {
                            productQuantity = c.getQuantity() + 1;
                        } else {
                            productQuantity = c.getQuantity() + quantity;
                        }
                        c.setQuantity(productQuantity);
                        checkExist = true;

                    }
                }
            }
            //else add new cart
            if (!checkExist) {
                listCarts.add(cart);
                checkExist = false;
            }
            session.setAttribute("product", p);
            session.setAttribute("listCarts", listCarts);

        }
        int check = 1;

        if (getQuantity != null) {
            request.setAttribute("check", check);
            request.setAttribute("productId", productId);
            request.getRequestDispatcher("detail").forward(request, response);
        } else if (getEdit != null) {
            request.setAttribute("edit", getEdit);
            request.getRequestDispatcher("view-cart").forward(request, response);
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
