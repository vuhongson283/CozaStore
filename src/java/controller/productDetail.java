/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
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
public class productDetail extends HttpServlet {

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
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categories;
        SupplierDAO supplierDAO = new SupplierDAO();
        ArrayList<Supplier> suppliers;
        SizeDAO sizeDAO = new SizeDAO();
        ArrayList<Size> sizes;
        getAllProductDAO dao = new getAllProductDAO();
        ProductDAO productSize = new ProductDAO();
        ArrayList<ProductSize> productSizes;
        try {
            categories = categoryDAO.getAllCategoryAddAndEdit();
            suppliers = supplierDAO.getAllSupplierAddAndEdit();
            sizes = sizeDAO.getAllSize();
            Product p = dao.getProductByID(id);
            productSizes = productSize.getProductSize(id);
            request.setAttribute("id", id);
            request.setAttribute("categories", categories);
            request.setAttribute("sizes", sizes);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("detail", p);
            request.setAttribute("productSizes", productSizes);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
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
