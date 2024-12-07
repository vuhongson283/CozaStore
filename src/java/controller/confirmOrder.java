/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.SignUpInDAO;
import Model.Cart;
import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author PCMSI
 */
public class confirmOrder extends HttpServlet {

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
            out.println("<title>Servlet confirmOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet confirmOrder at " + request.getContextPath() + "</h1>");
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
        CartDAO dao = new CartDAO();
        HttpSession session = request.getSession();
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        SignUpInDAO d = new SignUpInDAO();
        String email = acc.getUser_email();
        Users u = d.GetUser(email);
        request.setAttribute("profileuser", u);
        List<Cart> listPC0 = dao.getListCart(user_id);
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String amount = request.getParameter("amount");
        int check = 1;
        for(Cart cart :listPC0){
            if(Integer.parseInt(cart.getQuantity())==0){
                dao.removeItem(user_id, cart.getProduct_id(), cart.getSize_id());
            }
        }
        List<Cart> listPC = dao.getListCart(user_id);
        if(listPC.isEmpty()){
                        request.setAttribute("mess", "Your cart is empty! Please add product with the quantity more than 0 to check out!");
            request.getRequestDispatcher("cartDetail").forward(request, response);

        }
        else{
        for (Cart cart : listPC) {
            if (Integer.parseInt(cart.getQuantity()) > Integer.parseInt(cart.getStockofquantity())) {
                check = 0;
            }
        }
        if (check == 0) {
            request.setAttribute("mess", "Please check your cart again! The quantity of products exceeds the stock!");

            request.getRequestDispatcher("cartDetail").forward(request, response);

        } else {
//            request.setAttribute("listPC", listPC);
            session.removeAttribute("listOrder");
            session.removeAttribute("address");
            session.removeAttribute("phone");
            session.removeAttribute("amount");

            session.setAttribute("address", address);
            session.setAttribute("phone", phone);
            session.setAttribute("amount", amount);

            session.setAttribute("listOrder", listPC);
            request.getRequestDispatcher("confirmOrder.jsp").forward(request, response);
        }
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
