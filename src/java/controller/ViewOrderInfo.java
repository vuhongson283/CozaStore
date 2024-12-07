/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.AccountDAO;
import DAO.OrderDAO;
import Model.Order;
import Model.OrderDetail;
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
public class ViewOrderInfo extends HttpServlet {
   
   OrderDAO OrderDAO = new OrderDAO();
    AccountDAO AccountDAO = new AccountDAO();
    List<Orderstatus> OS = OrderDAO.getAllOrderStatus();
    List<OrderDetail> listOD = new ArrayList<>();

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
        HttpSession ss = request.getSession();
        String oid = request.getParameter("oid");
        Users users = (Users) ss.getAttribute("acc");
        Order o = OrderDAO.getOrderByOrderID(oid);
        Users u = AccountDAO.getUserByID(users.getUser_id());
        listOD = OrderDAO.getOrderDetailByOrderID(oid);

        request.setAttribute("OS", OS);
        request.setAttribute("order", o);
        request.setAttribute("u", u);
        request.setAttribute("listOD", listOD);
        request.getRequestDispatcher("ViewOrderInfo.jsp").forward(request, response);
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
