/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/create-account"})
public class CreateAccountController extends HttpServlet {

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
        String email = request.getParameter("email");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");

        boolean checkInsert = false;

        AccountDAO adb = new AccountDAO();
        String userDb = adb.getAccountByUserName(username);
        String emailDb = adb.getAccountByUserEmail(email);
        int checkUser =1;
        //check user and email not equals user and email in db
        if (username.equals(userDb) || email.equals(emailDb)) {           
            request.setAttribute("user", username);
            request.setAttribute("email", email);
            request.setAttribute("pass", password);
            if(username.equals(userDb)) {
                checkUser=2;
                request.setAttribute("checkUser", checkUser);
            }
             if(email.equals(emailDb)) {
                checkUser=3;
                request.setAttribute("checkUser", checkUser);
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        } else {
            
            checkInsert = adb.insertAccount(username, password, email);
        }
        if (checkInsert == true) {
            Account account = adb.getAccountByUsernameAndPassword(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("currentLogin", account);
            request.setAttribute("account", account);
            request.getRequestDispatcher("profileCreate.jsp").forward(request, response);
        } else {
            request.setAttribute("checkInsert", checkInsert);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
