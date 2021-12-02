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
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //check view login or action a login
        String loginAction = request.getParameter("loginAction");

        AccountDAO db = new AccountDAO();
        HttpSession sessionCurrentLogin = request.getSession(true);
        Account accountSession = (Account) sessionCurrentLogin.getAttribute("currentLogin");
        //check action or open form login
        if (loginAction == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //check session 
            if (accountSession != null) {
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                Account account = db.getAccountByUsernameAndPassword(username, password);
                if (account != null) {
                    switch (account.getRoleId()) {
                        //admin
                        case 1:
                            HttpSession adminsss = request.getSession();
                            adminsss.setAttribute("adminLogin", account);
                            adminsss.setMaxInactiveInterval(24 * 60 * 60);
                            request.setAttribute("account", account);
                            request.getRequestDispatcher("admin").forward(request, response);
                            break;
                        //user    
                        case 2:
                            db.updateStatus(1, account.getUsername());
                            HttpSession session = request.getSession();
                            session.setAttribute("currentLogin", account);
                            session.setMaxInactiveInterval(24 * 60 * 60);
                            request.setAttribute("account", account);
                            request.getRequestDispatcher("home").forward(request, response);
                            break;
                    }
                } else {
                    String statement = "Login failed!!!";
                    request.setAttribute("statement", statement);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            }
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
