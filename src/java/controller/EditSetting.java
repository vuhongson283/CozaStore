/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BlogDAO;
import DAO.ProductDAO;
import DAO.SettingDAO;
import DAO.SignUpInDAO;
import Model.Setting;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class EditSetting extends HttpServlet {

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
            out.println("<title>Servlet EditSetting</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSetting at " + request.getContextPath() + "</h1>");
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
    SettingDAO settingDAO = new SettingDAO();
    ProductDAO productDAO = new ProductDAO();
    BlogDAO blogDAO = new BlogDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        String settingId = request.getParameter("id");
        if (!settingId.matches("^[0-9]+$")) {
            settingId = "0";
        }
        try {
            Setting setting = settingDAO.getSetting(settingId);
            request.setAttribute("setting", setting);
            request.getRequestDispatcher("EditSetting.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditSetting.class.getName()).log(Level.SEVERE, null, ex);
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
        String settingId = request.getParameter("settingId");
        String settingCategory = request.getParameter("type");
        String settingOrder = request.getParameter("order");
        String settingValue = request.getParameter("value");
        String settingStatus = request.getParameter("status");
//        response.getWriter().print(settingId);
        try {
            settingDAO.updateSetting(settingId, settingCategory, settingOrder, settingValue, settingStatus);
            productDAO.updateProductStatus();
            blogDAO.updateBlogStatus();
        } catch (SQLException ex) {
            Logger.getLogger(EditSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
        String updateSuccess = "Update successfull!";
        request.setAttribute("updateSuccessfull", updateSuccess);
        request.getRequestDispatcher("settingmanagement").forward(request, response);
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
