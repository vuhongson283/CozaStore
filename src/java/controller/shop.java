/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.SizeDAO;
import DAO.SupplierDAO;
import DAO.getAllProductDAO;
import Helper.PaginationHelper;
import Model.Cart;
import Model.Category;
import Model.Product;
import Model.Size;
import Model.Supplier;
import Model.Users;
import Model.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PCMSI
 */
public class shop extends HttpServlet {

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
            out.println("<title>Servlet home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet home at " + request.getContextPath() + "</h1>");
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
    List<Product> pList = new ArrayList<>();
    SupplierDAO supplierDAO = new SupplierDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("search");
            session.removeAttribute("filter");
            getAllProductDAO dao = new getAllProductDAO();
            List<Supplier> listS = supplierDAO.getAllSupplierAddAndEdit();
            List<Product> listP = dao.getAllProduct();
            List<Category> listC = categoryDAO.getAllCategoryAddAndEdit();
            SizeDAO siDAO = new SizeDAO();

            List<Size> size = siDAO.getAllSizes();

            String indexPage = request.getParameter("page");
            CartDAO daoC = new CartDAO();
            Users acc = (Users) session.getAttribute("acc");
            PaginationHelper p = new PaginationHelper(listP, 8);
            if (indexPage == null) {
                indexPage = "1";
            }

            p.setCurrentPage(Integer.parseInt(indexPage));
            pList = p.getCurrentPageItems();
            if (acc == null) {
                request.setAttribute("totalP", listP.size());
                request.setAttribute("cp", p.getCurrentPage());
                request.setAttribute("endPage", p.getTotalPages());
                request.setAttribute("listP", pList);
                request.setAttribute("listS", listS);
                request.setAttribute("listC", listC);

                request.getRequestDispatcher("shop.jsp").forward(request, response);
            } else {
                String user_id = acc.getUser_id();
                        List<Wishlist> listW = daoC.getWishList(user_id);
                request.setAttribute("listWL", listW);

                List<Cart> listPC = daoC.getListCart(user_id);
                request.setAttribute("listPC", listPC);
                request.setAttribute("size", size);

                request.setAttribute("totalP", listP.size());
                request.setAttribute("cp", p.getCurrentPage());
                request.setAttribute("endPage", p.getTotalPages());
                request.setAttribute("listP", pList);
                request.setAttribute("listS", listS);
                request.setAttribute("listC", listC);

                request.getRequestDispatcher("shop.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(shop.class.getName()).log(Level.SEVERE, null, ex);
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
