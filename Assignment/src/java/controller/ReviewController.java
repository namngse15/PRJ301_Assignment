/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ReviewDAO;
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
import model.Review;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "ReviewController", urlPatterns = {"/review"})
public class ReviewController extends HttpServlet {

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
        ReviewDAO rdb = new ReviewDAO();

        String productId = request.getParameter("productId");
        String getRate = request.getParameter("stars");
        String message = request.getParameter("reviewmess");
        //get account from session
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("currentLogin");

        List<Review> listReviews = rdb.getAllReviewByProductId(productId);

        int rate = 0;
        int checkReviewExist = 0;
        boolean checkReview = false;

        try {
            rate = Integer.parseInt(getRate);
        } catch (NumberFormatException e) {
            System.out.println("er");
        }
        //check user review this product or not
        if (account == null) {
            response.sendRedirect("login.jsp");
        } else {
            Review r = new Review(rate, message, account.getId(), productId);
            if (!listReviews.isEmpty()) {
                for (Review l : listReviews) {
                    if (l.getAccountId() == account.getId() && l.getProductId().equals(productId)) {
                        checkReviewExist = 1;
                    }
                }
            }
            if (checkReviewExist == 0) {
                checkReview = rdb.insertReview(r);
            }
            request.setAttribute("checkReview", checkReview);
            request.setAttribute("checkReviewExist", checkReviewExist);
            request.getRequestDispatcher("detail?productId=" + productId).forward(request, response);
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
