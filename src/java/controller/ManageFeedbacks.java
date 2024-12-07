/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.FeedbackDAO;
import DAO.SignUpInDAO;
import Helper.PaginationHelper;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

/**
 *
 * @author ADMIN
 */
public class ManageFeedbacks extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //response.getWriter().print(list);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        String feedback_des = request.getParameter("content");
        String feedback_rate = request.getParameter("rate");
        String product_name = request.getParameter("product_name");
        String user_name = request.getParameter("user_name");
        String feedback_status = request.getParameter("status");
        
        List <Feedback> list ;
        List <Feedback> cList ;
        FeedbackDAO dao = new FeedbackDAO();
        list = dao.getFeedbackByCondition(feedback_des == null ? "": feedback_des, product_name == null ? "" : product_name, user_name == null ? "" : user_name, feedback_status == null ? "" : feedback_status, feedback_rate == null ? "" : feedback_rate);
        String indexPage = request.getParameter("page");
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        PaginationHelper p = new PaginationHelper(list, 2);
        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("totalUsers", list.size());
        request.setAttribute("list", cList);
        
        request.setAttribute("content", feedback_des);
        request.setAttribute("rate", feedback_rate);
        request.setAttribute("product_name", product_name);
        request.setAttribute("user_name", user_name);
        request.setAttribute("status", feedback_status);
        
        request.getRequestDispatcher("manageFeedback.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
        String feedback_des = request.getParameter("content");
        String feedback_rate = request.getParameter("rate");
        String product_name = request.getParameter("product_name");
        String user_name = request.getParameter("user_name");
        String feedback_status = request.getParameter("status");
        String indexPage = request.getParameter("page");
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        
        List <Feedback> list ;
        List <Feedback> cList ;
        FeedbackDAO dao = new FeedbackDAO();
        list = dao.getFeedbackByCondition(feedback_des == null ? "": feedback_des, product_name == null ? "" : product_name, user_name == null ? "" : user_name, feedback_status == null ? "" : feedback_status, feedback_rate == null ? "" : feedback_rate);
        PaginationHelper p = new PaginationHelper(list, 2);
        p.setCurrentPage(Integer.parseInt(indexPage));
        cList = p.getCurrentPageItems();
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("totalUsers", list.size());
        request.setAttribute("list", cList);
        request.setAttribute("content", feedback_des);
        request.setAttribute("rate", feedback_rate);
        request.setAttribute("product_name", product_name);
        request.setAttribute("user_name", user_name);
        request.setAttribute("status", feedback_status);
        
        request.getRequestDispatcher("manageFeedback.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
