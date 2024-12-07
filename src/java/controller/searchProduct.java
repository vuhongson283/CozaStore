/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.getAllProductDAO;
import Helper.PaginationHelper;
import Model.Category;
import Model.Product;
import Model.Supplier;
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
 * @author PCMSI
 */
public class searchProduct extends HttpServlet {

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
            out.println("<title>Servlet searchProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchProduct at " + request.getContextPath() + "</h1>");
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
    List<Product> pList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("filter");
        getAllProductDAO dao = new getAllProductDAO();
        String search = request.getParameter("search");
        List<Supplier> listS = dao.getAllBrand();
        List<Product> listP = dao.getProductBySearch(search);
        List<Category> listC = dao.getAllCategory();
        String indexPage = request.getParameter("page");
        PaginationHelper p = new PaginationHelper(listP, 8);
        if (indexPage == null) {
            indexPage = "1";
        }

        p.setCurrentPage(Integer.parseInt(indexPage));
        pList = p.getCurrentPageItems();
        if (listP.size() != 0) {
            request.setAttribute("totalP", listP.size());
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());
            request.setAttribute("listP", pList);
            request.setAttribute("listS", listS);
            request.setAttribute("listC", listC);
            session.setAttribute("search", search);
            request.setAttribute("search", search);

            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else {
            request.setAttribute("totalP", listP.size());
            request.setAttribute("cp", p.getCurrentPage());
            request.setAttribute("endPage", p.getTotalPages());
            request.setAttribute("listP", pList);
            request.setAttribute("listS", listS);
            request.setAttribute("listC", listC);
            session.setAttribute("search", search);
            request.setAttribute("search", search);
            request.setAttribute("mess", "Product not found!");
            request.getRequestDispatcher("shop.jsp").forward(request, response);
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
