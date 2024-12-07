/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.MD5;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class ChangePassAfterReset extends HttpServlet {

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
            out.println("<title>Servlet ChangePassAfterReset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassAfterReset at " + request.getContextPath() + "</h1>");
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
        try {
            String encodedUser = request.getParameter("code");
            String encodedTimestamp = request.getParameter("timeStamp");
            byte[] decodedBytes = Base64.getDecoder().decode(encodedTimestamp);
            String decodedTimestamp = new String(decodedBytes, StandardCharsets.UTF_8);
            long timestamp = Long.parseLong(decodedTimestamp);
            long currentTime = System.currentTimeMillis();
            long expirationTime = timestamp + 30 * 60 * 1000;
            if (currentTime >= expirationTime) {
                try ( PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error please try again</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                request.setAttribute("userCode", encodedUser);
                request.getRequestDispatcher("resetPass.jsp").forward(request, response);
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error please try again</h1>");
            out.println("</body>");
            out.println("</html>");

        }
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
        String newPass = request.getParameter("newpass");
        String cfPass = request.getParameter("cfpass");
        String userCode = request.getParameter("userCode");
        if (!newPass.equals(cfPass) || newPass.length() < 6 || cfPass.length() < 6) {
            String error = "Please confirm your pass correctly, must contains at least 6 character";
            request.setAttribute("invalidPass", error);
            request.getRequestDispatcher("resetPass.jsp").forward(request, response);
        } else {
            try {
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.changePassWithUserCode(userCode, newPass);
                response.sendRedirect("login?mod=1");
            } catch (Exception ex) {
                Logger.getLogger(ChangePassAfterReset.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
