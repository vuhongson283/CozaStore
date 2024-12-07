/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BlogCategoryDAO;
import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.SizeDAO;
import DAO.SliderDAO;
import DAO.SupplierDAO;
import DAO.getAllProductDAO;
import Model.BlogCategory;
import Model.Cart;
import Model.Category;
import Model.Product;
import Model.Size;
import Model.Slider;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PCMSI
 */
public class home extends HttpServlet {

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
    SupplierDAO supplierDAO = new SupplierDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getAllProductDAO dao = new getAllProductDAO();
            SizeDAO siDAO = new SizeDAO();
            List<Supplier> listS = supplierDAO.getAllSupplierAddAndEdit();
            List<Product> listP = dao.getNew16Product();
            List<Category> listC = categoryDAO.getAllCategoryAddAndEdit();
            List<Size> size = siDAO.getAllSizes();
            SliderDAO d = new SliderDAO();
            List<Slider> listSlide = d.getSlider();
            BlogCategoryDAO bd = new BlogCategoryDAO();
            List<BlogCategory> listB1 = bd.getBlogCategory12();
            List<BlogCategory> listB2 = bd.getBlogCategory35();
            CartDAO daoC = new CartDAO();
            HttpSession session = request.getSession();
            Users acc = (Users) session.getAttribute("acc");
            if (acc == null) {
                request.setAttribute("size", size);
                request.setAttribute("listB1", listB1);
                request.setAttribute("listB2", listB2);
                request.setAttribute("listSlide", listSlide);
                request.setAttribute("listP", listP);
                request.setAttribute("listS", listS);
                request.setAttribute("listC", listC);

                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                String user_id = acc.getUser_id();
//            List<Product> listPC = daoC.getListCart(user_id);

        List<Wishlist> listWL = daoC.getWishList(user_id);
                List<Cart> listPC = daoC.getListCart(user_id);
                request.setAttribute("listPC", listPC);
                request.setAttribute("size", size);
                request.setAttribute("listB1", listB1);
                request.setAttribute("listB2", listB2);
                request.setAttribute("listSlide", listSlide);
                request.setAttribute("listP", listP);
                request.setAttribute("listS", listS);
                request.setAttribute("listC", listC);
                request.setAttribute("listWL", listWL);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
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
