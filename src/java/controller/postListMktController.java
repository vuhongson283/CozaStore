/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.BlogDAO;
import DAO.SignUpInDAO;
import Helper.PaginationHelper;
import Model.Blog;
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
public class postListMktController extends HttpServlet {

    BlogDAO b = new BlogDAO();
    List<Blog> listB = new ArrayList<>();
    List<Blog> cList = new ArrayList<>();

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
            out.println("<title>Servlet postListMktController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet postListMktController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            b.deleteBlogById(request.getParameter("blog_id"));
        }
        listB = b.getAllBlog();
        String indexPage = request.getParameter("page");
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        PaginationHelper p = new PaginationHelper(listB, 5);
        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("totalUsers", listB.size());
        request.setAttribute("listB", cList);
        request.getRequestDispatcher("postListMkt.jsp").forward(request, response);
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
        String pl_content = request.getParameter("pl_content");
        String pl_author = request.getParameter("pl_author");
        String pl_title = request.getParameter("pl_title");
        String pl_status = request.getParameter("pl_status");
        String indexPage = request.getParameter("page");
        listB = b.getBlogByCon(pl_content, pl_author, pl_title, pl_status);
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        PaginationHelper p = new PaginationHelper(listB, 5);
        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("totalUsers", listB.size());
        request.setAttribute("pl_content", pl_content);
        request.setAttribute("pl_author", pl_author);
        request.setAttribute("pl_title", pl_title);
        request.setAttribute("pl_status", pl_status);
        request.setAttribute("listB", cList);
        request.getRequestDispatcher("postListMkt.jsp").forward(request, response);
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
