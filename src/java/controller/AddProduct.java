/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.ProductSizeDAO;
import DAO.SignUpInDAO;
import DAO.SizeDAO;
import DAO.SupplierDAO;
import Helper.UploadImage;
import Model.Category;
import Model.Size;
import Model.Supplier;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
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
 * @author Luu Bach
 */
public class AddProduct extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categories;
        SupplierDAO supplierDAO = new SupplierDAO();
        ArrayList<Supplier> suppliers;
        SizeDAO sizeDAO = new SizeDAO();
        ArrayList<Size> sizes;
        try {
            categories = categoryDAO.getAllCategoryAddAndEdit();
            suppliers = supplierDAO.getAllSupplierAddAndEdit();
            sizes = sizeDAO.getAllSize();
            request.setAttribute("sizes", sizes);
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("addproductpage.jsp").forward(request, response);
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
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            SupplierDAO supplierDAO = new SupplierDAO();
            String productName = request.getParameter("name");
            float productPrice = Float.parseFloat(request.getParameter("price"));
            float productSale = Float.parseFloat(request.getParameter("sale"));
            int productCategoryId = categoryDAO.getCategoryId(request.getParameter("category"));
            String productSupplierId = supplierDAO.getSupplierId(request.getParameter("supplier"));
            String productFeature = request.getParameter("description");

            UploadImage uploadImage = new UploadImage();
            String avatar = (String) uploadImage.uploadFile(request, "img_product");
//            response.getWriter().print(request.getParameter("size36"));
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
            ProductDAO productDAO = new ProductDAO();
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            try {
                productDAO.addNewProduct(productName, productPrice, productSale, productCategoryId, productSupplierId, productFeature, avatar);
                int newProductId = productDAO.getLastProductId();
                productSizeDAO.addProductSize(quantityList, newProductId);
//                response.getWriter().print(newProductId);

            } catch (Exception ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("manageproduct");
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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
