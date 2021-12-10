/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Product;
import model.Review;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "AdminProductDetailView", urlPatterns = {"/adminViewProductDetail"})
public class AdminProductDetailView extends HttpServlet {

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
        ReviewDAO rdb = new ReviewDAO();
        //view product detail
        String viewProductDetail = request.getParameter("viewProductDetail");
        String getPrice = request.getParameter("price");
        String getQuantity = request.getParameter("quantity");
        //view product review
        String viewProductReview = request.getParameter("viewProductReview");
        String productId = request.getParameter("productId");
        List<Review> listReviews = rdb.getAllReviewByProductId(productId);
        //view edit product
        String viewEditProduct = request.getParameter("editProduct");
        //view product detail
        int price = 0;
        int quantity = 0;
        int ram = 0;
        int hdd = 0;
        try {
            price = Integer.parseInt(getPrice);
        } catch (Exception e) {
        }
        try {
            quantity = Integer.parseInt(getQuantity);
        } catch (Exception e) {
        }

        Product product = pdb.getOneProduct(productId);

        //getlocale date
        ZoneId zid = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate lt = LocalDate.now(zid);
        String localdate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(lt);
        //view form edit product     
        if (viewEditProduct != null) {
            request.setAttribute("viewEditProduct", viewEditProduct);
        }
        //view product detail 
        if (viewProductDetail != null) {
            request.setAttribute("viewProductDetail", viewProductDetail);
            request.setAttribute("product", product);
            request.setAttribute("price", price);
            request.setAttribute("quantity", quantity);
            request.setAttribute("localDate", localdate);
        }
        if (viewProductReview != null) {
            request.setAttribute("viewProductReview", viewProductReview);
            request.setAttribute("listReviews", listReviews);
        }
        request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
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
