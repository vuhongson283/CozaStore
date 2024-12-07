/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.SignUpInDAO;
import DAO.UpdateProfileDAO;
import Model.Cart;
import Model.Users;
import Model.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PCMSI
 */
public class updateprofile extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

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
        SignUpInDAO d = new SignUpInDAO();
        Users acc = (Users) session.getAttribute("acc");
        String email = acc.getUser_email();
        Users u = d.GetUser(email);
        String user_id = u.getUser_id();

        CartDAO Cdao = new CartDAO();
        List<Wishlist> listW = Cdao.getWishList(user_id);
        request.setAttribute("listWL", listW);

        List<Cart> listPC = Cdao.getListCart(user_id);
        request.setAttribute("listPC", listPC);
        request.setAttribute("profileuser", u);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        SignUpInDAO d = new SignUpInDAO();
        Users acc = (Users) session.getAttribute("acc");
        Users u = d.GetUser(email);
        String user_id = u.getUser_id();

        CartDAO Cdao = new CartDAO();
        List<Wishlist> listW = Cdao.getWishList(user_id);
        request.setAttribute("listWL", listW);

        List<Cart> listPC = Cdao.getListCart(user_id);
        request.setAttribute("listPC", listPC);
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        if (!phone.matches("[0-9]{10}")) {
            request.setAttribute("phone", "Phone numbers must be 10 digits!");
            request.getRequestDispatcher("profileuser").forward(request, response);
        } else {
            UpdateProfileDAO dao = new UpdateProfileDAO();
            dao.updateProfile(name, gender, phone, address, email);
            request.setAttribute("mess", "Update Successfully!");
            request.getRequestDispatcher("profileuser").forward(request, response);
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
