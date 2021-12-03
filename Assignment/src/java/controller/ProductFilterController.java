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
@WebServlet(name = "ProductFilterController", urlPatterns = {"/filterproduct"})
public class ProductFilterController extends HttpServlet {

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
        //get category
        String sort = request.getParameter("sortId");
        String cate = request.getParameter("cateId");
        String brand = request.getParameter("brandId");
        String display = request.getParameter("displaySize");
        String process = request.getParameter("processId");
        String ram = request.getParameter("ramId");
        String hdd = request.getParameter("hddId");
        String price = request.getParameter("priceId");
        int sortId = 0;
        int cateId = 0;
        int brandId = 0;
        int displaySize = 0;
        int processId = 0;
        int ramId = 0;
        int hddId = 0;
        int priceId = 0;
        //parse category
        if (cate != null) {
            cateId = Integer.parseInt(cate);
        }
        if (brand != null) {
            brandId = Integer.parseInt(brand);
        }
        if (display != null) {
            displaySize = Integer.parseInt(display);
        }
        if (process != null) {
            processId = Integer.parseInt(process);
        }
        if (ram != null) {
            ramId = Integer.parseInt(ram);
        }
        if (hdd != null) {
            hddId = Integer.parseInt(hdd);
        }
        if (price != null) {
            priceId = Integer.parseInt(price);
        }
        System.out.println(" cate " + cateId);
        System.out.println(" brand " + brandId);

        //set attribute for category
        if (cateId != 0) {
            request.setAttribute("cateId", cateId);
        }
        if (brandId != 0) {
            request.setAttribute("brandId", brandId);
        }
        if (displaySize != 0) {
            request.setAttribute("displaySize", displaySize);
        }
        if (processId != 0) {
            request.setAttribute("processId", processId);
        }
        if (ramId != 0) {
            request.setAttribute("ramId", ramId);
        }
        if (hddId != 0) {
            request.setAttribute("hddId", hddId);
        }
        if (priceId != 0) {
            request.setAttribute("priceId", priceId);
        }
        //sort product
        try {
            sortId = Integer.parseInt(sort);
        } catch (NumberFormatException e) {
        }
        if (sortId != 0) {
            request.setAttribute("sortId", sortId);
        }
        //pagging product
        String indexPage = request.getParameter("pageIndex");
        int pageIndex = 1;
        try {
            pageIndex = Integer.parseInt(indexPage);
        } catch (NumberFormatException e) {
        }
        request.setAttribute("pageIndex", pageIndex);

        int pageSize = 12;
        int totalProduct = pdb.getCountTotalProductByAllCate(cateId, brandId, displaySize,
                processId, ramId, hddId, priceId);
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
            List<Product> listProducts = pdb.getAllProductByAllCategory(cateId, brandId, displaySize,
                    processId, ramId, hddId, priceId, pageIndex, pageSize, sortId);
            if (!listProducts.isEmpty()) {
                request.setAttribute("listProducts", listProducts);
            } else {
                request.setAttribute("listProducts", "null");
            }

            System.out.println(listProducts);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("next", next);
            request.setAttribute("back", back);
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
