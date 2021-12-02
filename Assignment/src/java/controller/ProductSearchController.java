/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "ProductSearchController", urlPatterns = {"/search"})
public class ProductSearchController extends HttpServlet {

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

        String search = request.getParameter("text");
        String sort = request.getParameter("sortId");
        int sortId = 0;
        //paging search by product name
        String indexPage = request.getParameter("pageIndex");
        int pageIndex = 1;
        try {
            pageIndex = Integer.parseInt(indexPage);
        } catch (Exception e) {
        }
        try {
            sortId = Integer.parseInt(sort);
        } catch (Exception e) {
        }
        
        int pageSize = 12;
        int totalProduct = pdb.getCountTotalProductBySearch(search);
        int totalPage = 0;
        int page = 0;

        if (totalProduct == 0) {
            request.setAttribute("totalProduct", totalProduct);
            request.setAttribute("mess", "No product found");
        } else {
            page = totalProduct % pageSize;
            totalPage = totalProduct / pageSize;
            if (page == 0) {
                totalPage = totalPage + 0;
            } else {
                totalPage = totalPage + 1;
            }
            int next = pageIndex + 1;
            int back = pageIndex - 1;
            
            List<Product> listProducts = pdb.getAllProductPaggingBySearch(search, pageIndex, pageSize, sortId);

            request.setAttribute("listProducts", listProducts);
            request.setAttribute("text", search);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("next", next);
            request.setAttribute("back", back);
            request.setAttribute("sortId", sortId);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
        
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
