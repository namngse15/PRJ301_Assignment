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
        
        String addType = request.getParameter("addType");
        String editType = request.getParameter("editType");
        String removeType = request.getParameter("removeType");
        
        String getDisplay = request.getParameter("display");
        String getCateId = request.getParameter("cateEditId");
        String cateName = request.getParameter("cateEditName");
        String getRemoveCateId = request.getParameter("removeCateById");
        
        boolean checkAddCate = false;
        boolean checkEditCate = false;
        boolean checkRemoveCate = false;
        
        int display = 0;
        int cateId = 0;
        int removeCateId = 0;
        //add
        try {
            display = Integer.parseInt(getDisplay);
        } catch (Exception e) {
        }
        //edit
        try {
            cateId = Integer.parseInt(getCateId);
        } catch (Exception e) {
        }
        //remove
        try {
            removeCateId = Integer.parseInt(getRemoveCateId);
        } catch (Exception e) {
        }
        //add
        if (add != null) {
            switch (addType) {
                case "brand":
                    checkAddCate = cdb.addBrand(cateName);
                    break;
                case "price":
                    checkAddCate = cdb.addPrice(cateName);
                    break;
                case "display":
                    checkAddCate = cdb.addDisplay(display);
                    break;
                case "category":
                    checkAddCate = cdb.addCategory(cateName);
                    break;
                case "processor":
                    checkAddCate = cdb.addProcessor(cateName);
                    break;
                case "ram":
                    checkAddCate = cdb.addRam(cateName);
                    break;
                case "gpu":
                    checkAddCate = cdb.addGpu(cateName);
                    break;
                case "hdd":
                    checkAddCate = cdb.addHdd(cateName);
                    break;
            }
        }
        //edit
        if (edit != null) {
            switch (editType) {
                case "brand":
                    checkEditCate = cdb.editBrand(cateId, cateName);
                    break;
                case "category":
                    checkEditCate = cdb.editCategory(cateId, cateName);
                    break;
                case "price":
                    checkEditCate = cdb.editPrice(cateId, cateName);
                    break;
                case "processor":
                    checkEditCate = cdb.editProcessor(cateId, cateName);
                    break;
                case "gpu":
                    checkEditCate = cdb.editGpu(cateId, cateName);
                    break;
                case "ram":
                    checkEditCate = cdb.editRam(cateId, cateName);
                    break;
                case "hdd":
                    checkEditCate = cdb.editHdd(cateId, cateName);
                    break;
            }
        }
        //remove
        if (removeType != null) {
            switch (removeType) {
                case "brand":
                    checkRemoveCate = cdb.removeBrand(removeCateId);
                    break;
                case "price":
                    checkRemoveCate = cdb.removePrice(removeCateId);
                    break;
                case "category":
                    checkRemoveCate = cdb.removeCategory(removeCateId);
                    break;
                case "display":
                    checkRemoveCate = cdb.removeDisplay(removeCateId);
                    break;
                case "processor":
                    checkRemoveCate = cdb.removeProcessor(removeCateId);
                    break;
                case "gpu":
                    checkRemoveCate = cdb.removeGpu(removeCateId);
                    break;
                case "ram":
                    checkRemoveCate = cdb.removeRam(removeCateId);
                    break;
                case "hdd":
                    checkRemoveCate = cdb.removeHdd(removeCateId);
                    break;
            }
        }
        
        request.getRequestDispatcher("admin-category").forward(request, response);
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
