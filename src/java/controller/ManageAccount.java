/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.SignUpInDAO;
import DAO.UpdateProfileDAO;
import Model.Roles;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import Helper.*;
import javafx.scene.control.Pagination;

/**
 *
 * @author Cao Duy Quân
 */
public class ManageAccount extends HttpServlet {

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
            out.println("<title>Servlet ManageAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAccount at " + request.getContextPath() + "</h1>");
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
    SignUpInDAO d = new SignUpInDAO();
    AccountDAO dA = new AccountDAO();
    List<Users> listU = new ArrayList<>();
    List<Roles> listR = dA.getAllRole();
    List<Users> cList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        String indexPage = request.getParameter("page");

        listU = dA.getUsersByAdmin(gender == null ? "" : gender, role == null ? "" : role, status == null ? "" : status, name == null ? "" : name, email == null ? "" : email, phone == null ? "" : phone);

        PaginationHelper p = new PaginationHelper(listU, 5);
        if (indexPage == null) {
            indexPage = "1";
        }

        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("totalUsers", listU.size());
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("gender", gender);
        request.setAttribute("role", role);
        request.setAttribute("status", status);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        session.setAttribute("listR", listR);
        request.setAttribute("listU", cList);
        
        request.getRequestDispatcher("managementaccount.jsp").forward(request, response);
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

        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        if (request.getParameter("phase").equals("1")) {
            String gender = request.getParameter("gender");
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            listU = dA.getUsersByAdmin(gender, role, status, name, email, phone);
            PaginationHelper p = new PaginationHelper(listU, 5);
            cList = p.getCurrentPageItems();
            request.setAttribute("totalUsers", listU.size());
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());
            request.setAttribute("gender", gender);
            request.setAttribute("role", role);
            request.setAttribute("status", status);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);

        } else {
            String u_name = request.getParameter("u_name");
            String u_email = request.getParameter("u_email");
            String u_pass = request.getParameter("u_pass");
            String u_address = request.getParameter("u_address");
            String u_phone = request.getParameter("u_phone");
            String u_gender = request.getParameter("u_gender");
            String u_role = request.getParameter("u_role");

            Users us = new Users();
            us.setUser_email(u_email);
            us.setUser_name(u_name);
            us.setUser_pass(u_pass);
            us.setUser_roleId(u_role);
            us.setUser_image("avatarauto.jpg");
            us.setUser_code(null);
            us.setUser_address(u_address);
            us.setUser_gender(u_gender);
            us.setUser_phone(u_phone);
            us.setUser_status("1");
            session.setAttribute("newus", us);
            if (d.checkAccountExisted(u_email)) {
                request.setAttribute("u_alert1", "Email already exists!");
                request.setAttribute("s", "1");
            } else if (!u_phone.matches("[0-9]{10}")) {
                request.setAttribute("u_cPhone", "Phone must be 10 digits!");
                request.setAttribute("s", "1");
            } else {
                d.AddUsers(us);
            }

            listU = dA.getUsersByAdmin("", "", "", "", "", "");
            PaginationHelper p = new PaginationHelper(listU, 5);
            cList = p.getCurrentPageItems();
            request.setAttribute("totalUsers", listU.size());
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());

        }
        request.setAttribute("profileuser", user);

        session.setAttribute("listR", listR);
        request.setAttribute("listU", cList);
        request.getRequestDispatcher("managementaccount.jsp").forward(request, response);
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
