/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.SignUpInDAO;
import Helper.PaginationHelper;
import Model.Order;
import Model.Orderstatus;
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
 * @author Cao Duy Quân
 */
public class ManageOrderBySale extends HttpServlet {

    OrderDAO OrderDAO = new OrderDAO();
    List<Orderstatus> listOS = OrderDAO.getAllOrderStatus();
    List<Order> listOrder = new ArrayList<>();
    List<Order> oList = new ArrayList<>();
    SignUpInDAO d = new SignUpInDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("acc");
        String emailu = u.getUser_email();
        Users user = d.GetUser(emailu);
        request.setAttribute("profileuser", user);
        String status = request.getParameter("status");
        String name = request.getParameter("name");
        String oid = request.getParameter("oid");
        String dateF = request.getParameter("dateF");
        String dateT = request.getParameter("dateT");
        listOrder = OrderDAO.getAllOrder(status == null ? "" : status, name == null ? "" : name, oid == null ? "" : oid, dateF == null ? "" : dateF, dateT == null ? "" : dateT);
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }

        PaginationHelper p = new PaginationHelper(listOrder, 7);
        p.setCurrentPage(Integer.parseInt(indexPage));
        oList = p.getCurrentPageItems();

        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("status", status);
        request.setAttribute("name", name);
        request.setAttribute("dateF", dateF);
        request.setAttribute("dateT", dateT);

        request.setAttribute("oid", oid);
        request.setAttribute("listO", oList);
        request.setAttribute("listOS", listOS);
        request.getRequestDispatcher("manageorderbysale.jsp").forward(request, response);
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
        request.setAttribute("profileuser", user);
        String status = request.getParameter("status");
        String name = request.getParameter("name");
        String oid = request.getParameter("oid");
        String dateF = request.getParameter("dateF");
        String dateT = request.getParameter("dateT");
        listOrder = OrderDAO.getAllOrder(status == null ? "" : status, name == null ? "" : name, oid == null ? "" : oid, dateF == null ? "" : dateF, dateT == null ? "" : dateT);

        PaginationHelper p = new PaginationHelper(listOrder, 7);
        oList = p.getCurrentPageItems();

        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("status", status);
        request.setAttribute("name", name);
        request.setAttribute("oid", oid);
        request.setAttribute("dateF", dateF);
        request.setAttribute("dateT", dateT);
        request.setAttribute("listO", oList);
        request.setAttribute("listOS", listOS);
        request.getRequestDispatcher("manageorderbysale.jsp").forward(request, response);

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
