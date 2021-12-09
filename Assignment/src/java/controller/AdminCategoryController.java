/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "AdminCategoryController", urlPatterns = {"/admin-category"})
public class AdminCategoryController extends HttpServlet {

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
        CategoryDAO cdb = new CategoryDAO();

        String viewEditCategory = request.getParameter("editCategory");
        request.setAttribute("viewEditCategory", viewEditCategory);
        //edit
        String getBrand = request.getParameter("brandId");
        String getCate = request.getParameter("cateId");
        String getAmount = request.getParameter("priceId");
        String getProcessor = request.getParameter("processorId");
        boolean checkEditCate = false;
        String cateName = "";
        int brandId = 0;
        int cateId = 0;
        int priceId = 0;
        int processorId = 0;

        //edit
        try {
            brandId = Integer.parseInt(getBrand);
        } catch (Exception e) {
        }
        try {
            cateId = Integer.parseInt(getCate);
        } catch (Exception e) {
        }
        try {
            priceId = Integer.parseInt(getAmount);
        } catch (Exception e) {
        }
        try {
            processorId = Integer.parseInt(getProcessor);
        } catch (Exception e) {
        }
        //edit
        if (brandId != 0) {
            request.setAttribute("cate", 0);
            request.setAttribute("id", brandId);
            cateName = cdb.getBrandById(brandId);
            request.setAttribute("name", cateName);
        } else if (cateId != 0) {
            request.setAttribute("cate", 1);
            request.setAttribute("id", cateId);
            cateName = cdb.getCategoryById(cateId);
            request.setAttribute("name", cateName);

        } else if (priceId != 0) {
            request.setAttribute("cate", 2);
            request.setAttribute("id", priceId);
            cateName = cdb.getPriceById(priceId);
            request.setAttribute("name", cateName);

        } else if (processorId != 0) {
            request.setAttribute("cate", 3);
            request.setAttribute("id", processorId);
            cateName = cdb.getProcessorById(processorId);
            request.setAttribute("name", cateName);

        }

        request.getRequestDispatcher("admin-listProduct").forward(request, response);
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
