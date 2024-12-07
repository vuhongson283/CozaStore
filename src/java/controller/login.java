/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CartDAO;
import DAO.MD5;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.SignUpInDAO;
import Model.Product;

import Model.Users;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TTT
 */
public class login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
    AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("mod") != null & request.getParameter("mod").equals("2")) {
            request.setAttribute("s", 1);
        }
        request.getRequestDispatcher("signInUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MD5 md5 = new MD5();
        if (request.getParameter("phase").equals("1")) {
            // sign in here
            String u_email = request.getParameter("u_email");
            String u_pass = md5.getMd5(request.getParameter("u_pass"));
            SignUpInDAO d = new SignUpInDAO();
            if (!u_email.isEmpty() && !u_pass.isEmpty()) {
                boolean checkUser = d.checkUser(u_email, u_pass);

                if (checkUser) {
                    Users u = d.GetUser(u_email);
                    request.setAttribute("profileuser", u);
                    session.setAttribute("user", u);
                    session.setAttribute("status", "going");

                    if (u.getUser_roleId().equalsIgnoreCase("4") && u.getUser_status().equalsIgnoreCase("1")) {
                        session.setAttribute("acc", u);
                        response.sendRedirect("home");
                    } else if (u.getUser_roleId().equalsIgnoreCase("4") && u.getUser_status().equalsIgnoreCase("0")) {
                        request.setAttribute("mess", "Your account is banned!");
                        request.getRequestDispatcher("signInUp.jsp").forward(request, response);
                    } else if (u.getUser_roleId().equalsIgnoreCase("1") && u.getUser_status().equalsIgnoreCase("1")) {
                        session.setAttribute("acc", u);
                        response.sendRedirect("DashboardAdmin");
                    } else if (u.getUser_roleId().equalsIgnoreCase("1") && u.getUser_status().equalsIgnoreCase("0")) {
                        request.setAttribute("mess", "Your account is banned!");
                        request.getRequestDispatcher("signInUp.jsp").forward(request, response);
                    } else if (u.getUser_roleId().equalsIgnoreCase("3") && u.getUser_status().equalsIgnoreCase("1")) {
                        session.setAttribute("acc", u);
                        response.sendRedirect("DashboardMkt");
                    } else if (u.getUser_roleId().equalsIgnoreCase("3") && u.getUser_status().equalsIgnoreCase("0")) {
                        request.setAttribute("mess", "Your account is banned!");
                        request.getRequestDispatcher("signInUp.jsp").forward(request, response);
                    } else if (u.getUser_roleId().equalsIgnoreCase("2") && u.getUser_status().equalsIgnoreCase("0")) {
                        request.setAttribute("mess", "Your account is banned!");
                        request.getRequestDispatcher("signInUp.jsp").forward(request, response);
                    } else if (u.getUser_roleId().equalsIgnoreCase("2") && u.getUser_status().equalsIgnoreCase("1")) {
                        session.setAttribute("acc", u);
                        response.sendRedirect("DashboardSale");
                    }
                } else {
                    request.setAttribute("u_email", u_email);
                    request.setAttribute("u_alert", "Account or Password is not correct! Please try again");
                    request.getRequestDispatcher("signInUp.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("u_email", u_email);
                request.setAttribute("u_alert", "Please inform email and password");
                request.getRequestDispatcher("signInUp.jsp").forward(request, response);
            }
        } else {
            //signUp here
            String u_name = request.getParameter("u_name");
            String u_email = request.getParameter("u_email");
            String u_pass = request.getParameter("u_pass");
            String u_repass = request.getParameter("u_repass");
            String u_gender = request.getParameter("gender");
            String u_phone = request.getParameter("u_phone");
            String u_address = request.getParameter("u_address");

            SignUpInDAO d = new SignUpInDAO();
            Users u = new Users();
            u.setUser_email(u_email);
            u.setUser_name(u_name);
            u.setUser_pass(u_pass);
            u.setUser_roleId("4");
            u.setUser_image("avatarauto.jpg");
            String code = UUID.randomUUID().toString();
            u.setUser_code(code);
            u.setUser_address(u_address);
            u.setUser_gender(u_gender);
            u.setUser_phone(u_phone);
            u.setUser_status("0");
            session.setAttribute("ui", u);
            request.setAttribute("u_repass", u_repass);

            if (d.checkAccountExisted(u_email)) {
                request.setAttribute("u_alert1", "Email already exists!");

            } else if (!u_phone.matches("[0-9]{10}")) {
                request.setAttribute("u_cPhone", "Phone must be 10 digits!");
            } else if (!u_pass.equalsIgnoreCase(u_repass)) {
                request.setAttribute("u_alert2", "Re-enter password does not match!");
            } else {
                final String username = "duyquan7b@gmail.com";
                final String password = "bjpc znor fvmc zrxh";
                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true");
                Session sessions = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                String emailTo = u_email;
                String emailSubject = "Verify Account";
                String emailContent = "You need to verify your account by clicking the link:"
                        + "http://localhost:9999/SWP-demo3/checkEmailSignUp?code=" + code + "&uemail=" + u_email;
                try {
                    Message message = new MimeMessage(sessions);
                    message.setFrom(new InternetAddress(username));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                    message.setSubject(emailSubject);
                    message.setText(emailContent);
                    Transport.send(message);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                d.AddUsers(u);
                HttpSession ss = request.getSession();
                Users u1 = accountDAO.getUserByEmail(u.getUser_email());
                ss.setAttribute("user", u1);
                request.setAttribute("u_alert3", "Pleases check your email!");
            }
            request.setAttribute("s", "1");
            request.getRequestDispatcher("signInUp.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
