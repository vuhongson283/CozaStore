/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.DashboardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TTT
 */
public class FilterTypeDate extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        DashboardDAO db = new DashboardDAO();
        String type_filter = request.getParameter("type_filter");
        String pro_date = request.getParameter("pro_date");
        if (type_filter.equals("0")) {
                out.println("<option>Last 5 years</option>");
                out.println("<option value=\"" + 0 + "\""+ ">Show</option>");
        }
        else {
            out.println("<option value=\"" + 1 + "\"" + (pro_date.equals("1") ? "selected" : "") + ">" + "Jan - Mar" + "</option>");
            out.println("<option value=\"" + 2 + "\"" + (pro_date.equals("2") ? "selected" : "") + ">" + "Apr - Jun" + "</option>");
            out.println("<option value=\"" + 3 + "\"" + (pro_date.equals("3") ? "selected" : "") + ">" + "Jul - Sep" + "</option>");
            out.println("<option value=\"" + 4 + "\"" + (pro_date.equals("4") ? "selected" : "") + ">" + "Oct - Dec" + "</option>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
