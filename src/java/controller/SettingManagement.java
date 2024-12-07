/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SettingCategoryDAO;
import DAO.SettingDAO;
import DAO.SignUpInDAO;
import Helper.PaginationHelper;
import Model.Setting;
import Model.SettingCategory;
import Model.Users;
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
 * @author Luu Bach
 */
public class SettingManagement extends HttpServlet {

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
            out.println("<title>Servlet SettingManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingManagement at " + request.getContextPath() + "</h1>");
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
    SettingCategoryDAO settingCategoryDAO = new SettingCategoryDAO();
    SettingDAO settingDAO = new SettingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        String value = request.getParameter("value");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String indexPage = request.getParameter("page");

        try {
            ArrayList<SettingCategory> settingCategorys = settingCategoryDAO.getAllSettingCategory();
            ArrayList<Setting> settingList = settingDAO.getAllSettingFilter(value == null ? "" : value, status == null ? "" : status, type == null ? "" : type);
            try {
                if (indexPage == null || Integer.parseInt(indexPage) <= 0 || settingList.size() == 0) {
                    indexPage = "1";
                }
            } catch (NumberFormatException e) {
                indexPage = "1";
            }
            PaginationHelper p = new PaginationHelper(settingList, 10);
            p.setCurrentPage(Integer.parseInt(indexPage));
            List<Setting> settingListInPage = p.getCurrentPageItems();
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());
            request.setAttribute("settingCategorys", settingCategorys);
            request.setAttribute("settingList", settingList);
            request.setAttribute("settingListInPage", settingListInPage);
            request.setAttribute("value", value);
            request.setAttribute("status", status);
            request.setAttribute("type", type);
            request.getRequestDispatcher("SettingList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SettingManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        String value = request.getParameter("value");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        try {
            ArrayList<SettingCategory> settingCategorys = settingCategoryDAO.getAllSettingCategory();
            ArrayList<Setting> settingList = settingDAO.getAllSettingFilter(value == null ? "" : value, status == null ? "" : status, type == null ? "" : type);
            PaginationHelper p = new PaginationHelper(settingList, 10);
            p.setCurrentPage(Integer.parseInt(indexPage));
            List<Setting> settingListInPage = p.getCurrentPageItems();
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());
            request.setAttribute("settingCategorys", settingCategorys);
            request.setAttribute("settingList", settingList);
            request.setAttribute("settingListInPage", settingListInPage);
            request.setAttribute("value", value);
            request.setAttribute("status", status);
            request.setAttribute("type", type);
            request.getRequestDispatcher("SettingList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SettingManagement.class.getName()).log(Level.SEVERE, null, ex);
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
