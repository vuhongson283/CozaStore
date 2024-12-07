/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import DAO.MD5;
import DAO.ChangePassDAO;
import Model.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author ADMIN
 */
public class changePassWord extends HttpServlet {
   
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
        HttpSession session = request.getSession();

        Users u = (Users) session.getAttribute("acc");
        //Admin ad = (Admin) session.getAttribute("acc");
        String old_pass = request.getParameter("oldpass");
        String u_pass = request.getParameter("newpass");
        String cf_pass = request.getParameter("cfpass");
        String u_email = u.getUser_email();
        //String ad_email = ad.getAdmin_email();
        ChangePassDAO dao = new ChangePassDAO();
        MD5 md = new MD5();
        if (!u.getUser_roleId().isEmpty()) {
            
            if (u.getUser_pass().equals(md.getMd5(old_pass)) && u_pass.equals(cf_pass) && u_pass.equals(cf_pass) && u_pass.length() >= 6 && cf_pass.length() >= 6) {
                dao.changePassUser(u_email,cf_pass);
                request.setAttribute("alertP", "Change pass successfuly");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            } else {
                request.setAttribute("alertP", "Please confirm your pass correctly, new pass and confirm pass must contains at least 6 character!");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
//        } else {
//            if (ad.getAdmin_roleId().equals("1")) {
//                if (ad.getAdmin_pass().equals(old_pass) && u_pass.equals(cf_pass)) {
//                    ad = dao.changePassAdmin(ad_email,cf_pass);
//                    request.setAttribute("alertP", "Change pass successfuly");
//                    request.getRequestDispatcher("changePass.jsp").forward(request, response);
//                } else {
//                    request.setAttribute("alertP", "Current pass or new pass doesn't pass!");
//                    request.getRequestDispatcher("changePass.jsp").forward(request, response);
//                }
//            }
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
        
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
