/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "ReviewEditController", urlPatterns = {"/editreview"})
public class ReviewEditController extends HttpServlet {

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

        String getId = request.getParameter("id");

        String productId = request.getParameter("productId");
        String getRate = request.getParameter("stars");
        String mess = request.getParameter("reviewmess");

        int id = 0;
        int rate = 0;
        boolean checkUpdate = false;
        int openModal = 0;

        try {
            id = Integer.parseInt(getId);
        } catch (Exception e) {
        }
        try {
            rate = Integer.parseInt(getRate);
        } catch (Exception e) {
        }

        if (id != 0 && rate != 0) {
           checkUpdate= rdb.updateReview(id, rate, mess);
        } else if (id != 0) {
            openModal = 1;
            request.setAttribute("id",id);
            request.setAttribute("openModal", openModal);
        }
        request.setAttribute("checkUpdate", checkUpdate);
        request.getRequestDispatcher("detail?productId=" + productId).forward(request, response);
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
