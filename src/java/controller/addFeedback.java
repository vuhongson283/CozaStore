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
import Helper.UploadImage;
import Model.Cart;
import Model.Category;
import Model.Feedback;
import Model.Product;
import Model.ProductSize;
import Model.Size;
import Model.Supplier;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

/**
 *
 * @author Cao Duy Quân
 */
public class addFeedback extends HttpServlet {

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
        HttpSession session = request.getSession();
        UploadImage uploadImage = new UploadImage();
        String avatar = (String) uploadImage.uploadFile(request, "img_product");
        String feedback_des = request.getParameter("review");
        String feedback_rate = request.getParameter("rating");
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        String pid = request.getParameter("pid");
        String product_size_id = request.getParameter("product_size_id");
        dao.addFeedback(feedback_des, feedback_rate, avatar, user_id, pid);
        dao.changeStatusFeedback(product_size_id);
        
        response.sendRedirect("myPurchase?status=3");
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

            String sizeFeedback = request.getParameter("sizeFeedback");
            String quantityFeedback = request.getParameter("quantityFeedback");
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
            String size_id = dao.SizeId(sizeFeedback);
            String product_size_id = dao.getProductSizeId(pid, size_id);
            if (acc == null) {
                request.setAttribute("product_size_id", product_size_id);
                request.setAttribute("sizeFeedback", sizeFeedback);
                request.setAttribute("quantityFeedback", quantityFeedback);
                request.setAttribute("ave", average);
                request.setAttribute("count", count);
                request.setAttribute("list", list);
                request.setAttribute("ls", listSize);
                request.setAttribute("c", cList);
                request.setAttribute("s", sList);
                request.setAttribute("pd", p);
                request.setAttribute("size", size);
                request.setAttribute("l5", list5);
//                response.getWriter().print(product_size_id);
                request.getRequestDispatcher("addFeedback.jsp").forward(request, response);
            } else {
                CartDAO daoC = new CartDAO();

                String user_id = acc.getUser_id();
//            List<Product> listPC = daoC.getListCart(user_id);
                List<Cart> listPC = daoC.getListCart(user_id);
                request.setAttribute("product_size_id", product_size_id);

                request.setAttribute("ave", average);
                request.setAttribute("count", count);
                request.setAttribute("list", list);
                request.setAttribute("sizeFeedback", sizeFeedback);
                request.setAttribute("quantityFeedback", quantityFeedback);
                request.setAttribute("listPC", listPC);
                request.setAttribute("ls", listSize);
                request.setAttribute("c", cList);
                request.setAttribute("s", sList);
                request.setAttribute("pd", p);
                request.setAttribute("size", size);
                request.setAttribute("l5", list5);
//                response.getWriter().print(product_size_id);
                request.getRequestDispatcher("addFeedback.jsp").forward(request, response);
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
