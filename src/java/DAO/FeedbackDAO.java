/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO {

    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Feedback> getFeedback() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedback_id, f.feedback_des, f.feedback_rate, f.feedback_img, f.feedback_status, "
                + "f.user_id, u.user_name, u.email, u.phone, "
                + "f.product_id, p.product_name "
                + "FROM feedback f "
                + "JOIN users u ON f.user_id = u.user_id "
                + "JOIN product p ON f.product_id = p.product_id";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedback_id(rs.getString("feedback_id"));
                feedback.setFeedback_des(rs.getString("feedback_des"));
                feedback.setFeedback_rate(rs.getString("feedback_rate"));
                feedback.setFeedback_img(rs.getString("feedback_img"));
                feedback.setFeedback_status(rs.getString("feedback_status"));
                feedback.setUser_id(rs.getString("user_id"));
                feedback.setUser_name(rs.getString("user_name"));
                feedback.setUser_email(rs.getString("email"));
                feedback.setUser_phone(rs.getString("phone"));
                feedback.setProduct_id(rs.getString("product_id"));
                feedback.setProduct_name(rs.getString("product_name"));
                list.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Feedback> getFeedbackByProductId(String product_id) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT  f.feedback_des, f.feedback_rate, f.feedback_img, f.feedback_status, \n"
                + "                  u.user_name, u.email, u.phone,\n"
                + "                  p.product_name \n"
                + "                 FROM feedback f \n"
                + "                 JOIN users u ON f.user_id = u.user_id \n"
                + "                 JOIN product p ON f.product_id = p.product_id\n"
                + "                 where p.product_id = ? and f.feedback_status = 1 ";

        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedback_des(rs.getString("feedback_des"));
                feedback.setFeedback_rate(rs.getString("feedback_rate"));
                feedback.setFeedback_img(rs.getString("feedback_img"));
                feedback.setFeedback_status(rs.getString("feedback_status"));
                feedback.setUser_name(rs.getString("user_name"));
                feedback.setUser_email(rs.getString("email"));
                feedback.setUser_phone(rs.getString("phone"));
                feedback.setProduct_name(rs.getString("product_name"));
                list.add(feedback);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public Feedback getFeedbackByID(String feedback_id) {
        Feedback feedback = null;
        String sql = "SELECT  f.feedback_des, f.feedback_rate, f.feedback_img, f.feedback_status, \n"
                + "                  u.user_name, u.email, u.phone,\n"
                + "                  p.product_name \n"
                + "                 FROM feedback f \n"
                + "                 JOIN users u ON f.user_id = u.user_id \n"
                + "                 JOIN product p ON f.product_id = p.product_id\n"
                + "                 where f.feedback_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, feedback_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                feedback = new Feedback(); 
                feedback.setFeedback_des(rs.getString("feedback_des"));
                feedback.setFeedback_rate(rs.getString("feedback_rate"));
                feedback.setFeedback_img(rs.getString("feedback_img"));
                feedback.setFeedback_status(rs.getString("feedback_status"));
                feedback.setUser_name(rs.getString("user_name"));
                feedback.setUser_email(rs.getString("email"));
                feedback.setUser_phone(rs.getString("phone"));
                feedback.setProduct_name(rs.getString("product_name"));
            }
        } catch (Exception e) {
        }
        return feedback;
    }
    
    public String getProductSizeId(String product_id, String size_id){
        String product_size_id = null;
        String sql = "SELECT product_size_id from product_size where product_id = ? AND size_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, product_id);
            ps.setString(2, size_id);
            rs = ps.executeQuery();
            if (rs.next()) {
            // Lấy giá trị product_size_id từ ResultSet
            product_size_id = rs.getString("product_size_id");
        }
        } catch (Exception e) {
        }
        return product_size_id;
    }
    public String SizeId(String size){
        String size_id = null;
        String sql = "SELECT size_id from onlineshop.size where size = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, size);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            // Lấy giá trị product_size_id từ ResultSet
            size_id = rs.getString("size_id");
        }
        } catch (Exception e) {
        }
        return size_id;
    }
    public void addFeedback(String feedback_des, String feedback_rate, String feedback_img, String user_id, String product_id){
        String sql = "INSERT feedback (feedback_des, feedback_rate, feedback_img, feedback_status, user_id, product_id ) values (?,?,?,?,?,?)" ;
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, feedback_des);
            ps.setString(2, feedback_rate);
            ps.setString(3, feedback_img);
            ps.setString(4, "1");
            ps.setString(5, user_id);
            ps.setString(6, product_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<Feedback> getFeedbackByCondition(String feedbackDes, String productName, String userName, String feedbackStatus, String feedbackRate) {
    List<Feedback> list = new ArrayList<>();
    String sql = "SELECT f.feedback_id, f.feedback_des, f.feedback_rate, f.feedback_img, f.feedback_status, "
                + "f.user_id, u.user_name, u.email, u.phone, "
                + "f.product_id, p.product_name "
                + "FROM feedback f "
                + "JOIN users u ON f.user_id = u.user_id "
                + "JOIN product p ON f.product_id = p.product_id "
                + "WHERE f.feedback_des LIKE ? "
                + "AND p.product_name LIKE ? "
                + "AND u.user_name LIKE ? "
                + "AND f.feedback_status LIKE ? "
                + "AND f.feedback_rate LIKE ?";
    try {
        c = new DBContext().getConnection();
        ps = c.prepareStatement(sql);
        ps.setString(1, "%" + feedbackDes + "%");
        ps.setString(2, "%" + productName + "%");
        ps.setString(3, "%" + userName + "%");
        ps.setString(4, "%" + feedbackStatus + "%");
        ps.setString(5, "%" + feedbackRate + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Feedback feedback = new Feedback();
            feedback.setFeedback_id(rs.getString("feedback_id"));
            feedback.setFeedback_des(rs.getString("feedback_des"));
            feedback.setFeedback_rate(rs.getString("feedback_rate"));
            feedback.setFeedback_img(rs.getString("feedback_img"));
            feedback.setFeedback_status(rs.getString("feedback_status"));
            feedback.setUser_id(rs.getString("user_id"));
            feedback.setUser_name(rs.getString("user_name"));
            feedback.setUser_email(rs.getString("email"));
            feedback.setUser_phone(rs.getString("phone"));
            feedback.setProduct_id(rs.getString("product_id"));
            feedback.setProduct_name(rs.getString("product_name"));
            list.add(feedback);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return list;
}
    public void changeStatusFeedback(String product_size_id){
    String sql = "Update orderdetail set status_feedback = 2 where product_size_id = ?";
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1, product_size_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
}
    
    public void banFeedback (String feedback_id){
        String sql = "Update feedback set feedback_status = 0 where feedback_id = ?" ;
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1, feedback_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void unbanFeedback (String feedback_id){
        String sql = "Update feedback set feedback_status = 1 where feedback_id = ?" ;
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1, feedback_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
//    public static void main(String[] args) {
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        
//        // Gọi phương thức getFeedback() từ FeedbackDAO
//        List<Feedback> feedbackList = feedbackDAO.getFeedback();
//        
//        // In ra thông tin từ danh sách feedback
//        for (Feedback feedback : feedbackList) {
//            System.out.println("Feedback ID: " + feedback.getFeedback_id());
//            System.out.println("Feedback Description: " + feedback.getFeedback_des());
//            System.out.println("Feedback Rate: " + feedback.getFeedback_rate());
//            System.out.println("Feedback Image: " + feedback.getFeedback_img());
//            System.out.println("Feedback Status: " + feedback.getFeedback_status());
//            System.out.println("User ID: " + feedback.getUser_id());
//            System.out.println("User Name: " + feedback.getUser_name());
//            System.out.println("User Email: " + feedback.getUser_email());
//            System.out.println("User Phone: " + feedback.getUser_phone());
//            System.out.println("Product ID: " + feedback.getProduct_id());
//            System.out.println("Product Name: " + feedback.getProduct_name());
//            System.out.println("------------------------------------");
//        }
//    }

}
