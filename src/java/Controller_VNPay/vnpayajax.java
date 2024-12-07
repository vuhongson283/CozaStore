/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller_VNPay;

import DAO.CartDAO;
import Helper.Convert;
import Model.Cart;
import Model.Order;
import Model.ProductSize;
import Model.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author PCMSI
 */
public class vnpayajax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users acc = (Users) session.getAttribute("acc");
        String user_id = acc.getUser_id();
        CartDAO dao = new CartDAO();
        List<Cart> listPC = (List<Cart>) session.getAttribute("listOrder");
        int count = 0;
        for (Cart cart : listPC) {
            ProductSize ps = dao.checkQuantityCheckOut(cart.getProduct_size_id());

            if (Integer.parseInt(cart.getQuantity()) > Integer.parseInt(ps.getQuantity())) {
                count = 1;
            }
        }
        if (count == 1) {
            request.setAttribute("mess", "Please check your cart again! The quantity of products exceeds the stock!");
            request.getRequestDispatcher("cartDetail").forward(request, response);

        } else {
            float total = 0;
            for (Cart cart : listPC) {
                total += Integer.parseInt(cart.getQuantity()) * Float.parseFloat(cart.getPrice()) * (100 - Float.parseFloat(cart.getSale_price())) / 100;
            }
            String address = (String) session.getAttribute("address");
            String phone = (String) session.getAttribute("phone");
//        String amount1= (String) session.getAttribute("amount");
//        Float amount2= Float.parseFloat(amount1);
            Convert convert = new Convert();
            String amount3 = (String) convert.convertUSDtoVND(total);

            String payment = "VNPay";
            if (user_id.isEmpty()) {
                request.setAttribute("mess", "Please login to purchase!");
                request.getRequestDispatcher("login?mod=1").forward(request, response);
            } else {
                dao.addOrderVNPay(user_id, address, phone, String.valueOf(total), payment);
            }
            Order order = dao.getOrderNow(user_id);
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = Long.parseLong(amount3) * 100;
            String bankCode = request.getParameter("bankCode");

            String vnp_TxnRef = order.getOrder_id() + "";
            String vnp_IpAddr = Config.getIpAddress(request);

            String vnp_TmnCode = Config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = request.getParameter("language");
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            com.google.gson.JsonObject job = new JsonObject();
            job.addProperty("code", "00");
            job.addProperty("message", "success");
            job.addProperty("data", paymentUrl);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(job));
            response.sendRedirect(paymentUrl);
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

}
