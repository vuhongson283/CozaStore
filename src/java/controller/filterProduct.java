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
public class filterProduct extends HttpServlet {

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
            out.println("<title>Servlet filterProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet filterProduct at " + request.getContextPath() + "</h1>");
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
        session.removeAttribute("search");
        getAllProductDAO dao = new getAllProductDAO();
        List<Supplier> listS = dao.getAllBrand();
        List<Category> listC = dao.getAllCategory();
        String sort = request.getParameter("sort") == null ? "" : request.getParameter("sort");
        String price = request.getParameter("price") == null ? "" : request.getParameter("price");
        String supplier = request.getParameter("supplier") == null ? "" : request.getParameter("supplier");
        String category = request.getParameter("category") == null ? "" : request.getParameter("category");
        String indexPage = request.getParameter("page");
        String min = "0", max = "9999999999";
        if (indexPage == null) {
            indexPage = "1";
        }
        if (price.equalsIgnoreCase("price1")) {
            min = "0";
            max = "50";
        } else if (price.equalsIgnoreCase("price2")) {
            min = "50";
            max = "100";
        } else if (price.equalsIgnoreCase("price3")) {
            min = "100";
            max = "150";
        } else if (price.equalsIgnoreCase("price4")) {
            min = "150";
            max = "200";
        } else if (price.equalsIgnoreCase("price5")) {
            min = "200";
            max = "9999999999";
        }

        if (!sort.isEmpty()) {
            if (sort.equalsIgnoreCase("1")) {

                List<Product> listP = dao.getProductSortDefault(supplier, category, min, max);
                PaginationHelper p = new PaginationHelper(listP, 8);
                p.setCurrentPage(Integer.parseInt(indexPage));
                pList = p.getCurrentPageItems();
                if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                }
            } else if (sort.equalsIgnoreCase("2")) {
                List<Product> listP = dao.getProductSortPopular(supplier, category, min, max);
                PaginationHelper p = new PaginationHelper(listP, 8);
                p.setCurrentPage(Integer.parseInt(indexPage));
                pList = p.getCurrentPageItems();
                if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                }
            } else if (sort.equalsIgnoreCase("3")) {
                List<Product> listP = dao.getProductSortNew(supplier, category, min, max);
                PaginationHelper p = new PaginationHelper(listP, 8);
                p.setCurrentPage(Integer.parseInt(indexPage));
                pList = p.getCurrentPageItems();
                if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                }
            } else if (sort.equalsIgnoreCase("4")) {
                List<Product> listP = dao.getProductSortLowToHigh(supplier, category, min, max);
                PaginationHelper p = new PaginationHelper(listP, 8);
                p.setCurrentPage(Integer.parseInt(indexPage));
                pList = p.getCurrentPageItems();
                if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                }
            } else if (sort.equalsIgnoreCase("5")) {
                List<Product> listP = dao.getProductSortHighToLow(supplier, category, min, max);
                PaginationHelper p = new PaginationHelper(listP, 8);
                p.setCurrentPage(Integer.parseInt(indexPage));
                pList = p.getCurrentPageItems();
                if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                }
            }
        } else {
            List<Product> listP = dao.getProductSortDefault(supplier, category, min, max);
            PaginationHelper p = new PaginationHelper(listP, 8);
            p.setCurrentPage(Integer.parseInt(indexPage));
            pList = p.getCurrentPageItems();
            if (listP.size() != 0) {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                } else {
                    request.setAttribute("totalP", listP.size());
                    request.setAttribute("cp", p.getCurrentPage());
                    request.setAttribute("endPage", p.getTotalPages());
                    request.setAttribute("listP", pList);
                    request.setAttribute("listS", listS);
                    request.setAttribute("listC", listC);
                    request.setAttribute("sort", sort);
                    request.setAttribute("price", price);
                    request.setAttribute("supplier", supplier);
                    request.setAttribute("category", category);
                    session.setAttribute("filter", "filter");
                    request.setAttribute("mess", "Product not found!");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
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
