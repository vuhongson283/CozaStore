/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import Model.Cart;
import Model.ProductSize;
import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author PCMSI
 */
public class checkOut extends HttpServlet {

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
            out.println("<title>Servlet checkOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOut at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        CartDAO dao = new CartDAO();
        List<Cart> listPC = (List<Cart>) session.getAttribute("listOrder");

        int count = 0;
        for (Cart cart : listPC) {
            ProductSize ps = dao.checkQuantityCheckOut(cart.getProduct_size_id());
            if (Integer.parseInt(cart.getQuantity()) > Integer.parseInt(ps.getQuantity())) {
                count = 1;
            }
        }
        
        if (count == 1) {
            request.setAttribute("mess", "Please check your cart again! The quantity of products exceeds the stock!");
            request.getRequestDispatcher("cartDetail").forward(request, response);

        } else {
            float total = 0;
            for (Cart cart : listPC) {
                total += Integer.parseInt(cart.getQuantity()) * Float.parseFloat(cart.getPrice()) * (100 - Float.parseFloat(cart.getSale_price())) / 100;
            }
            String address = (String) session.getAttribute("address");
            String phone = (String) session.getAttribute("phone");
            
            String payment = "Cash on delivery";

            if (user_id.isEmpty()) {
                request.setAttribute("mess", "Please login to purchase!");
                request.getRequestDispatcher("login?mod=1").forward(request, response);
            } else {
                dao.addOrder(listPC, user_id, address, phone, String.valueOf(total), payment);
                dao.newCart(user_id);
                request.setAttribute("mess", "Thank you for your order <3");
                request.getRequestDispatcher("myPurchase?status=1").forward(request, response);

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
