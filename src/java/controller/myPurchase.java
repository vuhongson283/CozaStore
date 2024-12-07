/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.OrderDAO;
import Helper.PaginationHelper;
import Model.Cart;
import Model.Order;
import Model.OrderDetail;
import Model.Orderstatus;
import Model.Users;
import Model.Wishlist;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCMSI
 */
public class myPurchase extends HttpServlet {

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
            out.println("<title>Servlet myPurchase</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myPurchase at " + request.getContextPath() + "</h1>");
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
        List<Order> pList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        OrderDAO dao = new OrderDAO();
        List<Orderstatus> listOs = dao.getAllOrderStatus();
        request.setAttribute("listOs", listOs);
        String oid = request.getParameter("status");
        CartDAO Cdao = new CartDAO();
        List<Wishlist> listW = Cdao.getWishList(user_id);
                request.setAttribute("listWL", listW);

                List<Cart> listPC = Cdao.getListCart(user_id);
                request.setAttribute("listPC", listPC);
        List<Order> listO = Cdao.getListOrderByStatus(oid, user_id);
        String indexPage = request.getParameter("page");
        PaginationHelper p = new PaginationHelper(listO, 8);
        if (indexPage == null) {
            indexPage = "1";
        }
         p.setCurrentPage(Integer.parseInt(indexPage));
        pList = p.getCurrentPageItems();
        request.setAttribute("totalP", listO.size());
        request.setAttribute("cp", p.getCurrentPage());
        request.setAttribute("endPage", p.getTotalPages());
        request.setAttribute("status", oid);
                request.setAttribute("listO", pList);

        request.getRequestDispatcher("myPurchase.jsp").forward(request, response);
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
