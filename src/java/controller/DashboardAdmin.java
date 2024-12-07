/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.DashboardDAO;
import DAO.SignUpInDAO;
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
public class DashboardAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    List<String> listYear = new ArrayList<>();
    DashboardDAO db = new DashboardDAO();
    List<Integer> listYearUse = new ArrayList<>();
    double orderProfit = db.getOrderProfit();
    int totalUser = db.getTotalUsers();
    int feedbackHighly = db.getFeedbackHighly();

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
            out.println("<title>Servlet DashboardMkt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardMkt at " + request.getContextPath() + "</h1>");
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
        listYear = db.getYearFilters();
        feedbackHighly = db.getFeedbackHighly();
        totalUser = db.getTotalUsers();
        orderProfit = db.getOrderProfit();
        List<Integer> listYearUse1 = new ArrayList<>();
        for (int i = 0; i < listYear.size(); i++) {
            int cnt = 0;
            for (int k = i + 1; k < listYear.size(); k++) {
                if (listYear.get(i).equals(listYear.get(k))) {
                    cnt++;
                    break;
                }
            }
            if (cnt == 0) {
                listYearUse1.add(Integer.parseInt(listYear.get(i)));
            }
        }
        for (int i = 0; i < listYearUse1.size(); i++) {
            for (int k = i + 1; k < listYearUse1.size(); k++) {
                if (listYearUse1.get(i) < listYearUse1.get(k)) {
                    int tg = listYearUse1.get(i);
                    listYearUse1.set(i, listYearUse1.get(k));
                    listYearUse1.set(k, tg);
                }
            }
        }
        listYearUse = listYearUse1;
        request.setAttribute("listOrCom", db.getProSoldByYear());
        request.setAttribute("listOrPay", db.getProStock());
        request.setAttribute("listYear", db.getYear());
        request.setAttribute("dbChoose", 1);
        request.setAttribute("orderProfit", orderProfit);
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("feedbackHighly", feedbackHighly);
        request.setAttribute("product_date_list", listYearUse);
        request.getRequestDispatcher("DashboardAdmin.jsp").forward(request, response);
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
        String products_date = request.getParameter("products_date");
        String type_filter = request.getParameter("type_filter");
        List<String> listOrCom = new ArrayList<>();
        List<String> listOrPay = new ArrayList<>();
        List<String> listYear1 = new ArrayList<>();
        switch (products_date) {
            case "0": {
                response.sendRedirect("DashboardAdmin");
            }
            case "1": {
                listOrCom = db.getProSoldQuarter(type_filter, 1, 3);
                listOrPay = db.getRevenueQuarter(type_filter, 1, 3);
                listYear1.add("January");
                listYear1.add("February");
                listYear1.add("March");
                break;
            }
            case "2": {
                listOrCom = db.getProSoldQuarter(type_filter, 4, 6);
                listOrPay = db.getRevenueQuarter(type_filter, 4, 6);
                listYear1.add("April");
                listYear1.add("May");
                listYear1.add("June");
                break;
            }
            case "3": {
                listOrCom = db.getProSoldQuarter(type_filter, 7, 9);
                listOrPay = db.getRevenueQuarter(type_filter, 7, 9);
                listYear1.add("July");
                listYear1.add("August");
                listYear1.add("September");
                break;
            }
            case "4": {
                listOrCom = db.getProSoldQuarter(type_filter, 10, 12);
                listOrPay = db.getRevenueQuarter(type_filter, 10, 12);
                listYear1.add("October");
                listYear1.add("November");
                listYear1.add("December");
                break;
            }
        }
        request.setAttribute("listOrCom", listOrCom);
        request.setAttribute("listOrPay", listOrPay);
        request.setAttribute("listYear", listYear1);
        request.setAttribute("dbChoose", 2);
        request.setAttribute("year_date_choose", type_filter);
        request.setAttribute("quarter_date_choose", products_date);
        request.setAttribute("orderProfit", orderProfit);
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("feedbackHighly", feedbackHighly);
        request.setAttribute("product_date_list", listYearUse);
        request.getRequestDispatcher("DashboardAdmin.jsp").forward(request, response);
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
