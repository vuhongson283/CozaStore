/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BlogCategoryDAO;
import DAO.BlogDAO;
import DAO.SignUpInDAO;
import Helper.UploadImage;
import Model.Blog;
import Model.BlogCategory;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
/**
 *
 * @author TTT
 */
public class AddPostController extends HttpServlet {

    BlogCategoryDAO bc = new BlogCategoryDAO();
    List<BlogCategory> blogCate = new ArrayList<>();
    BlogDAO b = new BlogDAO();

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
            out.println("<title>Servlet AddPostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPostController at " + request.getContextPath() + "</h1>");
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
        blogCate = bc.getAllBlogCategory1();
        LocalDate current = java.time.LocalDate.now();
        request.setAttribute("b_updateDate", current);
        request.setAttribute("blogCategoriesList", blogCate);
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            Blog blog = b.getBlogById(request.getParameter("blog_id"));
            request.setAttribute("b_blogId", blog.getBlogId());
            request.setAttribute("b_authorName", blog.getAuthorName());
            request.setAttribute("b_updateDate", blog.getUpdatedDate());
            request.setAttribute("blog_title", blog.getTitle());
            request.setAttribute("blogCategories", blog.getCategoryBlogId());
            request.setAttribute("blog_content", blog.getContent());
            request.setAttribute("blog_brief", blog.getBriefInfo());
            request.setAttribute("blog_thumbnail", blog.getThumbnail());
            request.setAttribute("status", "edit");
            request.getRequestDispatcher("newBlogAndEdit.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "new");
            request.getRequestDispatcher("newBlogAndEdit.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SignUpInDAO d = new SignUpInDAO();
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
//        String blog_thumbnail = request.getParameter("blog_thumbnail");
        LocalDate current = java.time.LocalDate.now();
        String b_authorName = request.getParameter("b_authorName");
        String b_updateDate = current.toString();
        String blog_title = request.getParameter("blog_title");
        String blogCategories = request.getParameter("blogCategories");
        String blog_content = request.getParameter("blog_content");
        String blog_brief = request.getParameter("blog_brief");
        UploadImage uploadImage = new UploadImage();
        String blog_thumbnail1 = (String) uploadImage.uploadFile(request, "images");
        if (request.getParameter("blog_id") != null) {
            String blog_id = request.getParameter("blog_id");
            b.updatePost(blog_thumbnail1, b_authorName, b_updateDate, blog_title, blogCategories, blog_content, blog_brief, blog_id);
        } else {
            b.addPost(blog_thumbnail1, b_authorName, b_updateDate, blog_title, blogCategories, blog_content, blog_brief);
        }
        response.sendRedirect("manageblog");
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
