/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import dal.ReviewDAO;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "AdminProductUpdate", urlPatterns = {"/admin-update-product"})
public class AdminProductUpdate extends HttpServlet {

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

        String add = request.getParameter("add");
        String edit = request.getParameter("edit");
        //productId remove
        String removeProductId = request.getParameter("removeProductById");
        //add and edit product img
        String productImg1 = request.getParameter("productImg1");
        String productImg2 = request.getParameter("productImg2");
        //add and edit product entity 
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String getBrand = request.getParameter("brandId");
        String getCate = request.getParameter("cateId");
        String getAmount = request.getParameter("priceId");
        String getPrice = request.getParameter("productPrice");
        String getQuantity = request.getParameter("productQuan");
        String productOs = request.getParameter("productOs");
        String getOsId = request.getParameter("osId");
        String productPort = request.getParameter("productPort");
        String getDSize = request.getParameter("displaySize");
        String getProcessor = request.getParameter("processorId");
        String displayDetail = request.getParameter("productDisplay");
        String processDetail = request.getParameter("productCpu");
        String ram = request.getParameter("productRam");
        String getramId = request.getParameter("ramId");
        String gpu = request.getParameter("productGpu");
        String getgpuId = request.getParameter("gpuId");
        String hardDrive = request.getParameter("productHd");
        String gethddId = request.getParameter("hddId");
        String battery = request.getParameter("productBattery");
        String note = request.getParameter("note");
        String color = request.getParameter("productColor");
        //id
        int brandId = 0;
        int cateId = 0;
        int osId = 0;
        int priceId = 0;
        int productPrice = 0;
        int productQuantity = 0;
        int displaySize = 0;
        int processorId = 0;
        int ramId = 0;
        int gpuId = 0;
        int hddId = 0;

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
            osId = Integer.parseInt(getOsId);
        } catch (Exception e) {
        }
        try {
            productPrice = Integer.parseInt(getPrice);
        } catch (Exception e) {
        }
        try {
            productQuantity = Integer.parseInt(getQuantity);
        } catch (Exception e) {
        }
        try {
            displaySize = Integer.parseInt(getDSize);
        } catch (Exception e) {
        }
        try {
            processorId = Integer.parseInt(getProcessor);
        } catch (Exception e) {
        }
        try {
            ramId = Integer.parseInt(getramId);
        } catch (Exception e) {
        }
        try {
            hddId = Integer.parseInt(gethddId);
        } catch (Exception e) {
        }
        try {
            gpuId = Integer.parseInt(getgpuId);
        } catch (Exception e) {
        }
        Product p;
        if (note == null || note.isEmpty()) {
            p = new Product(productId, productName, cateId, brandId, osId, productOs, displaySize, displayDetail, processorId, processDetail,
                    gpuId, gpu, ramId, ram, hddId, hardDrive, productPort, battery, priceId, productPrice, productQuantity, color);
        } else {
            p = new Product(productId, productName, cateId, brandId, osId, productOs, displaySize, displayDetail, processorId, processDetail,
                    gpuId, gpu, ramId, ram, hddId, hardDrive, productPort, battery, priceId, productPrice, productQuantity, note, color);
        }
        System.out.println(note);
        Boolean checkAddProductWithImg = false;
        Boolean checkEditProduct = false;
        Boolean checkEditProductWithImg = false;

        if (add != null) {
            Boolean checkAddProduct = pdb.addProduct(p);
            if (checkAddProduct) {
                checkAddProductWithImg = pdb.addProductWithImg(p.getId(), productImg1, productImg2);
            }
        } else if (edit != null) {
            checkEditProduct = pdb.updateProduct(p);
            if (productImg1 != null) {
                checkEditProductWithImg = pdb.updateProductWithImg(p.getId(), productImg1, productImg2);
            }
        }
        if (removeProductId != null) {
            List<Review> listReview = rdb.getAllReviewByProductId(removeProductId);
            //boolean checkReProduct = pdb.removeProductbyId(removeProductId, listReview);
        }
        request.getRequestDispatcher("admin-product").forward(request, response);
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
