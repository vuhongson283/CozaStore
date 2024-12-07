/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.ProductSizeDAO;
import Model.Cart;
import Model.ProductSize;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PCMSI
 */
public class addToCart extends HttpServlet {

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
            out.println("<title>Servlet addToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCart at " + request.getContextPath() + "</h1>");
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
        CartDAO dao = new CartDAO();
        ProductSizeDAO d = new ProductSizeDAO();
        HttpSession session = request.getSession();
        String pid = request.getParameter("pid");
        String size = request.getParameter("size");
        String quantity = request.getParameter("num-product");
//        String user_id = request.getParameter("user_id");
        Users acc = (Users) session.getAttribute("acc");
        
        ProductSize ps = d.getQuantity(pid, size);
        if (acc == null) {
            request.setAttribute("mess", "Please login to purchase!");
            request.getRequestDispatcher("login?mod=1").forward(request, response);
        } else {
                    String user_id = acc.getUser_id();

            Cart checkCart = dao.checkCart(user_id, pid, size);
            if (checkCart == null) {
                if (Integer.parseInt(quantity) <= Integer.parseInt(ps.getQuantity())) {
                    dao.addToCart(user_id, size, pid, quantity);
                    request.getRequestDispatcher("ShowProductDetail?pid=" + pid).forward(request, response);
                } else {
                    request.setAttribute("mess", "The number of products added exceed the quantity in stock!");
                    request.getRequestDispatcher("ShowProductDetail?pid=" + pid).forward(request, response);
                }
            } else {
                int updatequantity = Integer.parseInt(checkCart.getQuantity()) + Integer.parseInt(quantity);
                String getquantity = String.valueOf(updatequantity);
                if (updatequantity <= Integer.parseInt(checkCart.getStockofquantity())) {
                    dao.updateQuantity(user_id, pid, size, getquantity);
                    request.getRequestDispatcher("ShowProductDetail?pid=" + pid).forward(request, response);
                } else {
                    request.setAttribute("mess", "The number of products added exceed the quantity in stock!");
                    request.getRequestDispatcher("ShowProductDetail?pid=" + pid).forward(request, response);
                }
            }
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
