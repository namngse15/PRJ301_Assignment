/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
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
@WebServlet(name = "AdminCategoryUpdate", urlPatterns = {"/admin-cate-crud"})
public class AdminCategoryUpdate extends HttpServlet {

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
        String add = request.getParameter("add");
        String edit = request.getParameter("edit");

        String getAddType = request.getParameter("category");
        String getCateId = request.getParameter("cateId");
        String cateName = request.getParameter("cateName");
        String getDisplay = request.getParameter("display");
        String getEditType = request.getParameter("editType");
        String getRemoveType = request.getParameter("removeType");
        String getRemoveCateId = request.getParameter("removeCateById");

        boolean checkAddCate = false;
        boolean checkEditCate = false;
        boolean checkRemoveCate = false;

        int display = 0;
        int addType = 0;
        int editType = 0;
        int removeType = 0;
        int cateId = 0;
        int removeCateId = 0;
        //add
        try {
            addType = Integer.parseInt(getAddType);
        } catch (Exception e) {
        }
        try {
            display = Integer.parseInt(getDisplay);
        } catch (Exception e) {
        }
        //edit
        try {
            editType = Integer.parseInt(getEditType);
        } catch (Exception e) {
        }
        try {
            cateId = Integer.parseInt(getCateId);
        } catch (Exception e) {
        }
        //remove
        try {
            removeType = Integer.parseInt(getRemoveType);
        } catch (Exception e) {
        }
        try {
            removeCateId = Integer.parseInt(getRemoveCateId);
        } catch (Exception e) {
        }
        //add
        if (add != null) {
            switch (addType) {
                case 0:
                    checkAddCate = cdb.addBrand(cateName);
                    break;
                case 1:
                    checkAddCate = cdb.addPrice(cateName);
                    break;
                case 2:
                    checkAddCate = cdb.addDisplay(display);
                    break;
                case 3:
                    checkAddCate = cdb.addCategory(cateName);
                    break;
                case 4:
                    checkAddCate = cdb.addProcessor(cateName);
                    break;
            }
        }
        //edit
        if (edit != null) {
            switch (editType) {
                case 0:
                    checkEditCate = cdb.editBrand(cateId, cateName);
                    break;
                case 1:
                    checkEditCate = cdb.editCategory(cateId, cateName);
                    break;
                case 2:
                    checkEditCate = cdb.editPrice(cateId, cateName);
                    break;
                case 3:
                    checkEditCate = cdb.editProcessor(cateId, cateName);
                    break;

            }
        }
        //remove
        if (getRemoveType != null) {
            switch (removeType) {
                case 0:
                    checkRemoveCate = cdb.removeBrand(removeCateId);
                    break;
                case 1:
                    checkRemoveCate = cdb.removePrice(removeCateId);
                    break;
                case 2:
                    checkRemoveCate = cdb.removeCategory(removeCateId);
                    break;
                case 3:
                    checkRemoveCate = cdb.removeDisplay(removeCateId);
                    break;
                case 4:
                    checkRemoveCate = cdb.removeProcessor(removeCateId);
                    break;
            }
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
