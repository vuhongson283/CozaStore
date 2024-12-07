/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.SignUpInDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import util.Mail;

/**
 *
 * @author Luu Bach
 */
public class ResetPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> An email has been sent to your email. Check your Gmail to continue ! </h1>");
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
        request.getRequestDispatcher("enterEmail.jsp").forward(request, response);
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
        SignUpInDAO signUpInDAO = new SignUpInDAO();
        try {
            if (signUpInDAO.checkAccountExisted(request.getParameter("gmail")) == false) {
                String error = "Invalid Gmail , please try again!";
                request.setAttribute("invalidGmail", error);
                request.getRequestDispatcher("enterEmail.jsp").forward(request, response);
            } else {
                String notification = "Check your Gmail to continue";
                String code = UUID.randomUUID().toString();
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.changeEncodedAccount(request.getParameter("gmail"), code);
                request.setAttribute("notification", notification);
                sendMessage(request.getParameter("gmail"), code);
                request.getRequestDispatcher("enterEmail.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
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

    Session newSession = null;
    MimeMessage mimeMessage = null;
    
    private void sendMessage(String userGmail, String accountCode) throws MessagingException {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail(userGmail, accountCode);
        mail.sendEmail();
    }
    
}
