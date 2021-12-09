/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Product;
import model.Review;

/**
 *
 * @author tenhik
 */
@WebServlet(name = "AdminProductUpdate", urlPatterns = {"/admin-product"})
public class AdminProductViewByCateController extends HttpServlet {

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
        //view product detail
        String viewProductDetail = request.getParameter("viewProductDetail");
        String getPrice = request.getParameter("price");
        String getQuantity = request.getParameter("quantity");
        //view product by each category type
        String viewProductBrand = request.getParameter("viewProductByBrand");
        String getBrandId = request.getParameter("brandId");
        String viewProductPrice = request.getParameter("viewProductByPrice");
        String getPriceId = request.getParameter("priceId");
        String viewProductCate = request.getParameter("viewProductByCate");
        String getCateId = request.getParameter("cateId");
        String viewProductDiplay = request.getParameter("viewProductByDisplay");
        String getDisplayId = request.getParameter("displaySize");
        String viewProductProcess = request.getParameter("viewProductByProcess");
        String getProcessId = request.getParameter("processId");
        //view product review
        String viewProductReview = request.getParameter("viewProductReview");
        String productId = request.getParameter("productId");
        List<Review> listReviews = rdb.getAllReviewByProductId(productId);
        //view edit product
        String viewEditProduct = request.getParameter("editProduct");
        //view product detail
        int price = 0;
        int quantity = 0;
        int ram = 0;
        int hdd = 0;
        try {
            price = Integer.parseInt(getPrice);
        } catch (Exception e) {
        }
        try {
            quantity = Integer.parseInt(getQuantity);
        } catch (Exception e) {
        }
        //view product by each category type
        int priceId = 0;
        int brandId = 0;
        int displaySize = 0;
        int cpu = 0;
        int cate = 0;
        try {
            priceId = Integer.parseInt(getPriceId);
        } catch (NumberFormatException e) {
        }
        try {
            brandId = Integer.parseInt(getBrandId);
        } catch (NumberFormatException e) {
        }
        try {
            displaySize = Integer.parseInt(getDisplayId);
        } catch (NumberFormatException e) {
        }
        try {
            cpu = Integer.parseInt(getProcessId);
        } catch (NumberFormatException e) {
        }
        try {
            cate = Integer.parseInt(getCateId);
        } catch (NumberFormatException e) {
        }

        int countProductByCate = pdb.getCountTotalProductByAllCate(cate, brandId, displaySize, cpu, ram, hdd, priceId);
        List<Product> listProductsByCate = pdb.getAllProductByCategory(cate, brandId, displaySize, cpu, priceId);
        Product product = pdb.getOneProduct(productId);

        //getlocale date
        ZoneId zid = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate lt = LocalDate.now(zid);
        String localdate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(lt);
        //view form edit product     
        if (viewEditProduct != null) {
            request.setAttribute("viewEditProduct", viewEditProduct);
        }
        //view product detail 
        if (viewProductDetail != null) {
            request.setAttribute("viewProductDetail", viewProductDetail);
            request.setAttribute("product", product);
            request.setAttribute("price", price);
            request.setAttribute("quantity", quantity);
            request.setAttribute("localDate", localdate);
        }
        //view product each category type
        if (viewProductBrand != null) {
            request.setAttribute("countProductByCate", countProductByCate);
            request.setAttribute("viewProductBrand", viewProductBrand);
            request.setAttribute("listProductByCate", listProductsByCate);
        }
        if (viewProductPrice != null) {
            request.setAttribute("countProductByCate", countProductByCate);
            request.setAttribute("viewProductPrice", viewProductPrice);
            request.setAttribute("listProductByCate", listProductsByCate);
        }
        if (viewProductCate != null) {
            request.setAttribute("countProductByCate", countProductByCate);
            request.setAttribute("viewProductCate", viewProductCate);
            request.setAttribute("listProductByCate", listProductsByCate);
        }
        if (viewProductDiplay != null) {
            request.setAttribute("countProductByCate", countProductByCate);
            request.setAttribute("viewProductDisplay", viewProductDiplay);
            request.setAttribute("listProductByCate", listProductsByCate);
        }
        if (viewProductProcess != null) {
            request.setAttribute("countProductByCate", countProductByCate);
            request.setAttribute("viewProductProcess", viewProductProcess);
            request.setAttribute("listProductByCate", listProductsByCate);
        }
        if (viewProductReview != null) {
            request.setAttribute("viewProductReview", viewProductReview);
            request.setAttribute("listReviews", listReviews);
        }
        request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
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
