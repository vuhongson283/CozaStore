/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.SignUpInDAO;
import Helper.PaginationHelper;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TTT
 */
public class customerListController extends HttpServlet {

    AccountDAO d = new AccountDAO();
    List<Users> listU = d.getUsersByRole(4);
    List<Users> cList = new ArrayList<>();

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
            out.println("<title>Servlet customerListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet customerListController at " + request.getContextPath() + "</h1>");
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
        SignUpInDAO dao = new SignUpInDAO();
        Users u = (Users) session.getAttribute("acc");
        String email = u.getUser_email();
        Users user = dao.GetUser(email);
        String s_name = request.getParameter("s_name");
        String s_email = request.getParameter("s_email");
        String s_phone = request.getParameter("s_phone");
        String s_gender = request.getParameter("s_gender");
        String s_status = request.getParameter("s_status");
        String indexPage = request.getParameter("page");
        List<Users> listR = d.getUsersByRole(4);
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        if (listU.size() == listR.size()) {
            listU = listR;
        }
        PaginationHelper p = new PaginationHelper(listU, 5);
        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("listU", cList);
        request.setAttribute("totalUsers", listU.size());
        request.setAttribute("profileuser", user);

        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("s_name", s_name);
        request.setAttribute("s_email", s_email);
        request.setAttribute("s_phone", s_phone);
        request.setAttribute("s_status", s_status);
        request.setAttribute("s_gender", s_gender);
        request.getRequestDispatcher("managementcus.jsp").forward(request, response);
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
        AccountDAO d = new AccountDAO();
        String s_name = request.getParameter("s_name");
        String s_email = request.getParameter("s_email");
        String s_phone = request.getParameter("s_phone");
        String s_gender = request.getParameter("s_gender");
        String s_status = request.getParameter("s_status");
        String indexPage = request.getParameter("page");
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        listU = d.getCusByCon(s_name, s_email, s_phone, s_status, s_gender);
        PaginationHelper p = new PaginationHelper(listU, 5);
        p.setCurrentPage(Integer.parseInt(indexPage));
        request.setAttribute("totalUsers", listU.size());
        cList = p.getCurrentPageItems();
        request.setAttribute("listU", cList);
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("s_name", s_name);
        request.setAttribute("s_email", s_email);
        request.setAttribute("s_phone", s_phone);
        request.setAttribute("s_status", s_status);
        request.setAttribute("s_gender", s_gender);
        request.getRequestDispatcher("managementcus.jsp").forward(request, response);
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
