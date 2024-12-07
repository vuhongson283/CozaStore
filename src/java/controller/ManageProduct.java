package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.SignUpInDAO;
import DAO.SupplierDAO;
import Model.Category;
import Model.Product;
import Model.Supplier;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class ManageProduct extends HttpServlet {

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
            out.println("<title>Servlet ManageProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageProduct at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDao = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        String paramSearch = request.getParameter("search");
        String categorySearch = request.getParameter("category");
        String statusSearch = request.getParameter("status");
        try {
            ArrayList<Product> products = new ArrayList<>();
            ArrayList<Category> categories = categoryDAO.getAllCategory();
            ArrayList<Supplier> suppliers = supplierDAO.getAllSupplier();
            String indexPage = request.getParameter("page");
            if (indexPage == null || indexPage.isEmpty()) {
                indexPage = "1";
            }
            int page = Integer.parseInt(indexPage);
            int count = productDao.getAllProductByFilter(paramSearch == null ? "" : paramSearch, categorySearch == null ? "" : categorySearch, statusSearch == null ? "" : statusSearch).size();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage += 1;
            }
            products = productDao.getProductFilter(paramSearch == null ? "" : paramSearch, categorySearch == null ? "" : categorySearch, statusSearch == null ? "" : statusSearch, page);
            request.setAttribute("totalProductsInPage", count);
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("search", paramSearch);
            request.setAttribute("category", categorySearch);
            request.setAttribute("status", statusSearch);
            request.setAttribute("cp", page);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("manageproduct.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageProduct.class.getName()).log(Level.SEVERE, null, ex);
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
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        CategoryDAO categoryDAO = new CategoryDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        String paramSearch = request.getParameter("search");
        String categorySearch = request.getParameter("category");
        String statusSearch = request.getParameter("status");
        ProductDAO productDao = new ProductDAO();
        try {

            int count = productDao.getAllProductByFilter(paramSearch == null ? "" : paramSearch, categorySearch == null ? "" : categorySearch, statusSearch == null ? "" : statusSearch).size();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage += 1;
            }
            ArrayList<Product> products = productDao.getProductFilter(paramSearch, categorySearch, statusSearch, 1);
            ArrayList<Category> categories = categoryDAO.getAllCategory();
            ArrayList<Supplier> suppliers = supplierDAO.getAllSupplier();
            request.setAttribute("totalProductsInPage", count);
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("products", products);
            request.setAttribute("search", paramSearch);
            request.setAttribute("category", categorySearch);
            request.setAttribute("status", statusSearch);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("manageproduct.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageProduct.class.getName()).log(Level.SEVERE, null, ex);
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
