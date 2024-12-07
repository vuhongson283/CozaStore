/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import Helper.UploadImage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
/**
 *
 * @author ADMIN
 */
public class editProductt extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        ProductDAO productDAO = new ProductDAO();
        String pid = request.getParameter("id");
        String pname = request.getParameter("name");
        String pdes = request.getParameter("description");
        String pprice = request.getParameter("price");
        String psale = request.getParameter("sale");
        String cid = request.getParameter("category");
        String psup = request.getParameter("supplier");
        UploadImage uploadImage = new UploadImage();
        String avatar = (String) uploadImage.uploadFile(request, "img_product");
        String status = request.getParameter("status");
        getAllProductDAO dao = new getAllProductDAO();

        if (avatar.isEmpty()) {
            dao.editProductWithoutImg(pid, pname, pprice, psale, cid, psup, pdes, status);
            productDAO.updateProductStatus();
            response.sendRedirect("manageproduct");
        } else {

            dao.editProduct(pid, pname, pprice, psale, cid, psup, avatar, pdes, status);
            productDAO.updateProductStatus();
            response.sendRedirect("manageproduct");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editProductt.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String pid = request.getParameter("id");
            String pname = request.getParameter("name");
            String pdes = request.getParameter("description");
            String pprice = request.getParameter("price");
            String psale = request.getParameter("sale");
            String cid = request.getParameter("category");
            String psup = request.getParameter("supplier");
            UploadImage uploadImage = new UploadImage();
            String avatar = (String) uploadImage.uploadFile(request, "img_product");
            String status = request.getParameter("status");
            getAllProductDAO dao = new getAllProductDAO();
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            int size36Quantity = Integer.parseInt(request.getParameter("size36"));
            int size37Quantity = Integer.parseInt(request.getParameter("size37"));
            int size38Quantity = Integer.parseInt(request.getParameter("size38"));
            int size39Quantity = Integer.parseInt(request.getParameter("size39"));
            int size40Quantity = Integer.parseInt(request.getParameter("size40"));
            int size41Quantity = Integer.parseInt(request.getParameter("size41"));
            int size42Quantity = Integer.parseInt(request.getParameter("size42"));
            int size43Quantity = Integer.parseInt(request.getParameter("size43"));
            ArrayList<Integer> quantityList = new ArrayList<>();
            quantityList.add(size36Quantity);
            quantityList.add(size37Quantity);
            quantityList.add(size38Quantity);
            quantityList.add(size39Quantity);
            quantityList.add(size40Quantity);
            quantityList.add(size41Quantity);
            quantityList.add(size42Quantity);
            quantityList.add(size43Quantity);
            productSizeDAO.editProductSize(quantityList, Integer.parseInt(pid));
            //response.getWriter().print(quantityList);

            if (avatar.isEmpty()) {
                dao.editProductWithoutImg(pid, pname, pprice, psale, cid, psup, pdes, status);
                response.sendRedirect("manageproduct");
            } else {
                dao.editProduct(pid, pname, pprice, psale, cid, psup, avatar, pdes, status);
                response.sendRedirect("manageproduct");
            }
        } catch (SQLException ex) {
            Logger.getLogger(editProductt.class.getName()).log(Level.SEVERE, null, ex);
        }
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
