/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SignUpInDAO;
import DAO.SliderDAO;
import Helper.PaginationHelper;
import Model.Slider;
import Model.Users;
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
public class sliderControl extends HttpServlet {

    List<Slider> list;
    List<Slider> cList;
    SliderDAO dao = new SliderDAO();

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
        String statusParam = request.getParameter("status");
        String titleParam = request.getParameter("title");
        String backlinkParam = request.getParameter("backlink");
        list = dao.getSliderByCon(statusParam == null ? "" : statusParam, titleParam == null ? "" : titleParam, backlinkParam == null ? "" : backlinkParam);
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
        request.setAttribute("listS", cList);

        request.setAttribute("status", statusParam);
        request.setAttribute("title", titleParam);
        request.setAttribute("backlink", backlinkParam);
        request.getRequestDispatcher("manageSlider.jsp").forward(request, response);
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
        String statusParam = request.getParameter("status");
        String titleParam = request.getParameter("title");
        String backlinkParam = request.getParameter("backlink");
        list = dao.getSliderByCon(statusParam == null ? "" : statusParam, titleParam == null ? "" : titleParam, backlinkParam == null ? "" : backlinkParam);
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
        request.setAttribute("listS", cList);
        request.setAttribute("status", statusParam);
        request.setAttribute("title", titleParam);
        request.setAttribute("backlink", backlinkParam);
        request.getRequestDispatcher("manageSlider.jsp").forward(request, response);
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
