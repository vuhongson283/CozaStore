/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ProductDAO;
import DAO.ProductSizeDAO;
import DAO.SizeDAO;
import DAO.SupplierDAO;
import Model.Cart;
import Model.Category;
import Model.Feedback;
import Model.Product;
import Model.ProductSize;
import Model.Size;
import Model.Supplier;
import Model.Users;
import Model.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cao Duy Quân
 */
public class ShowProductDetail extends HttpServlet {

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
            out.println("<title>Servlet ShowProductDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowProductDetail at " + request.getContextPath() + "</h1>");
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
    ProductDAO pDAO = new ProductDAO();
    SupplierDAO sDAO = new SupplierDAO();
    CategoryDAO cDAO = new CategoryDAO();
    ProductSizeDAO pzDAO = new ProductSizeDAO();
    SizeDAO siDAO = new SizeDAO();
    FeedbackDAO dao = new FeedbackDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pid = request.getParameter("pid");
            Product p = pDAO.getProductByID(pid);
            HttpSession session = request.getSession();
            Users acc = (Users) session.getAttribute("acc");
            List<Feedback> list = dao.getFeedbackByProductId(pid);
            List<Category> cList = cDAO.getAllCategory();
            List<Supplier> sList = sDAO.getAllSupplier();
            List<Product> list5 = pDAO.get5RelatedProduct(p.getProduct_id(), p.getCategory_id());
            List<ProductSize> listSize = pzDAO.getAllSize(p.getProduct_id());
            List<Size> size = siDAO.getAllSizes();
            int count = list.size();
            double sum = 0;
            double average = 0;
            if (list == null || list.isEmpty()) {
                average = 0;
            } else {
                for (Feedback f : list) {
                    sum += Integer.parseInt(f.getFeedback_rate());
                }
                average = sum / count;
            }
            if (acc == null) {
                request.setAttribute("ave", average);
                request.setAttribute("count", count);
                request.setAttribute("list", list);

                request.setAttribute("ls", listSize);
                request.setAttribute("c", cList);
                request.setAttribute("s", sList);
                request.setAttribute("pd", p);
                request.setAttribute("size", size);
                request.setAttribute("l5", list5);

                request.getRequestDispatcher("product-detail-view.jsp").forward(request, response);
            } else {
                CartDAO daoC = new CartDAO();

                String user_id = acc.getUser_id();
//            List<Product> listPC = daoC.getListCart(user_id);
                List<Wishlist> listW = daoC.getWishList(user_id);
                request.setAttribute("listWL", listW);
                List<Cart> listPC = daoC.getListCart(user_id);
                request.setAttribute("ave", average);
                request.setAttribute("count", count);
                request.setAttribute("list", list);

                request.setAttribute("listPC", listPC);
                request.setAttribute("ls", listSize);
                request.setAttribute("c", cList);
                request.setAttribute("s", sList);
                request.setAttribute("pd", p);
                request.setAttribute("size", size);
                request.setAttribute("l5", list5);

                request.getRequestDispatcher("product-detail-view.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ShowProductDetail.class.getName()).log(Level.SEVERE, null, ex);
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
