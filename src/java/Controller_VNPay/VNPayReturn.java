/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller_VNPay;

import DAO.CartDAO;
import DAO.OrderDAO;
import Model.Cart;
import Model.Order;
import Model.OrderDetail;
import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PCMSI
 */
public class VNPayReturn extends HttpServlet {

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
            out.println("<title>Servlet VNPayReturn</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VNPayReturn at " + request.getContextPath() + "</h1>");
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
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        HttpSession session = request.getSession();
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        CartDAO order = new CartDAO();
        OrderDAO dao =new OrderDAO();
                
        List<Cart> listPC = (List<Cart>) session.getAttribute("listOrder");

        String signValue = Config.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                order.addOrderVNPaySuccess(listPC, user_id);
               order.newCart(user_id);
               request.setAttribute("mess", "Thank you for your order <3");
//                order.updateOrderPaymentStatus(Integer.parseInt(request.getParameter("vnp_TxnRef")), 2);
                        request.getRequestDispatcher("myPurchase?status=1").forward(request, response);

            } else {
                String status="4";
                order.updateOrderStatus(request.getParameter("vnp_TxnRef"), status);
                                order.addOrderVNPaySuccess(listPC, user_id);
Order ordernew=order.getOrderNow(user_id);
                String order_id=ordernew.getOrder_id();
                List<OrderDetail> listOD = dao.getOrderDetailByOrderID(order_id);
                for (OrderDetail orderDetail : listOD) {
                dao.UpdateQuantityForProductSize(orderDetail.getQuantity(), orderDetail.getProductSizeId());
            }
                        request.getRequestDispatcher("myPurchase?status=4").forward(request, response);
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
