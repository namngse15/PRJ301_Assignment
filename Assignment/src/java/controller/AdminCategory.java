/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
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

/**
 *
 * @author tenhik
 */
@WebServlet(name = "AdminCategory", urlPatterns = {"/admin-category"})
public class AdminCategory extends HttpServlet {

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

        ZoneId zid = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate lt = LocalDate.now(zid);
        String localdate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(lt);

        request.setAttribute("localDate", localdate);

        String viewAddCategory = request.getParameter("addCategory");
        request.setAttribute("viewAddCategory", viewAddCategory);
        String addType = request.getParameter("cateType");
        request.setAttribute("addType", addType);

        String viewEditCategory = request.getParameter("editCategory");
        request.setAttribute("viewEditCategory", viewEditCategory);
        //open edit modal
        String getBrand = request.getParameter("brandId");
        String getCate = request.getParameter("cateId");
        String getAmount = request.getParameter("priceId");
        String getProcessor = request.getParameter("processorId");
        String getGpu = request.getParameter("gpuId");
        String getRam = request.getParameter("ramId");
        String getHdd = request.getParameter("hddId");

        boolean checkEditCate = false;
        String cateName = "";
        int brandId = 0;
        int cateId = 0;
        int priceId = 0;
        int processorId = 0;
        int gpuId = 0;
        int ramId = 0;
        int hddId = 0;

        //edit attribute
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
        try {
            gpuId = Integer.parseInt(getGpu);
        } catch (Exception e) {
        }
        try {
            ramId = Integer.parseInt(getRam);
        } catch (Exception e) {
        }
        try {
            hddId = Integer.parseInt(getHdd);
        } catch (Exception e) {
        }

        //edit
        if (brandId != 0) {
            request.setAttribute("cate", "brand");
            request.setAttribute("id", brandId);
            cateName = cdb.getBrandById(brandId);
            request.setAttribute("name", cateName);
        } else if (cateId != 0) {
            request.setAttribute("cate", "category");
            request.setAttribute("id", cateId);
            cateName = cdb.getCategoryById(cateId);
            request.setAttribute("name", cateName);
        } else if (priceId != 0) {
            request.setAttribute("cate", "price");
            request.setAttribute("id", priceId);
            cateName = cdb.getPriceById(priceId);
            request.setAttribute("name", cateName);

        } else if (processorId != 0) {
            request.setAttribute("cate", "processor");
            request.setAttribute("id", processorId);
            cateName = cdb.getProcessorById(processorId);
            request.setAttribute("name", cateName);
        } else if (gpuId != 0) {
            request.setAttribute("cate", "gpu");
            request.setAttribute("id", gpuId);
            cateName = cdb.getGpuById(gpuId);
            request.setAttribute("name", cateName);
        } else if (ramId != 0) {
            request.setAttribute("cate", "ram");
            request.setAttribute("id", ramId);
            cateName = cdb.getGpuById(gpuId);
            request.setAttribute("name", cateName);
        } else if (hddId != 0) {
            request.setAttribute("cate", "hdd");
            request.setAttribute("id", hddId);
            cateName = cdb.getHddById(hddId);
            request.setAttribute("name", cateName);
        }
        request.getRequestDispatcher("adminCategory.jsp").forward(request, response);
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
