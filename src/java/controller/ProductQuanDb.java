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
public class ProductQuanDb extends HttpServlet {

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
        DashboardDAO db = new DashboardDAO();
        String pro_date = request.getParameter("pro_date");
        String type_filter = request.getParameter("type_filter");
        List<String> listOrCom = new ArrayList<>();
        List<String> listOrPay = new ArrayList<>();
        List<String> listYear = new ArrayList<>();

        switch (pro_date) {
            case "1": {
                listOrCom = db.getOrByQuarter(type_filter, 1, 3, 3);
                listOrPay = db.getOrByQuarter(type_filter, 1, 3, 1);
                listYear.add("January");
                listYear.add("February");
                listYear.add("March");
                break;
            }
            case "2": {
                listOrCom = db.getOrByQuarter(type_filter, 4, 6, 3);
                listOrPay = db.getOrByQuarter(type_filter, 4, 6, 1);
                listYear.add("April");
                listYear.add("May");
                listYear.add("June");
                break;
            }
            case "3": {
                listOrCom = db.getOrByQuarter(type_filter, 7, 9, 3);
                listOrPay = db.getOrByQuarter(type_filter, 7, 9, 1);
                listYear.add("July");
                listYear.add("August");
                listYear.add("September");
                break;
            }
            case "4": {
                listOrCom = db.getOrByQuarter(type_filter, 10, 12, 3);
                listOrPay = db.getOrByQuarter(type_filter, 10, 12, 1);
                listYear.add("October");
                listYear.add("November");
                listYear.add("December");
                break;
            }
        }
        for (String i : listOrCom) {
            out.println("<input name=\"OrCom\" value=\"" + i + "\" hidden=\"\">");
        }
        for (String i : listOrPay) {
            out.println("<input name=\"OrPay\" value=\"" + i + "\" hidden=\"\">");
        }
        for (String i : listYear) {
            out.println("<input name=\"YearsAgo\" value=\"" + i + "\" hidden=\"\">");
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
