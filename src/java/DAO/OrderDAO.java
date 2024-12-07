/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Order;
import Model.OrderDetail;
import Model.Orderstatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cao Duy Quân
 */
public class OrderDAO extends DBContext {

    public List<Orderstatus> getAllOrderStatus() {

        List<Orderstatus> listOs = new ArrayList<>();
        String sql = "SELECT * FROM onlineshop.orderstatus;";
        Connection c = getConnection();

        try {
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Orderstatus o = new Orderstatus();
                o.setStatus_id(rs.getString(1));
                o.setStatus_name(rs.getString(2));
                listOs.add(o);
            }
            return listOs;
        } catch (SQLException ex) {
            System.out.println("getAllOrderStatus: " + ex.getMessage());
        }
        return null;

    }

    public List<Order> getAllOrder(String status, String cusName, String oid, String dateF, String dateT) {
        try {
            String sql = "select o.order_id,u.user_id,u.user_name,o.order_date,o.address,o.total,o.payment,o.status\n"
                    + "from orders o\n"
                    + "left join users u on o.user_id = u.user_id\n"
                    + "where o.status  like ? and   u.user_name like ? and o.order_id like ? ";
            if (!dateF.isEmpty() && dateT.isEmpty()) {
                sql += "and o.order_date >= ?";
            } else if (dateF.isEmpty() && !dateT.isEmpty()) {
                sql += "and o.order_date <= ?";
            } else if (!dateF.isEmpty() && !dateT.isEmpty()) {
                sql += "and o.order_date >= ? and o.order_date <= ?";
            }
            List<Order> listO = new ArrayList<>();
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            int index = 1;
            ptm.setString(index++, "%" + status + "%");
            ptm.setString(index++, "%" + cusName + "%");
            ptm.setString(index++, "%" + oid + "%");
            if (!dateF.isEmpty() && dateT.isEmpty()) {
                ptm.setString(index++, dateF);
            } else if (dateF.isEmpty() && !dateT.isEmpty()) {
                ptm.setString(index++, dateT);
            } else if (!dateF.isEmpty() && !dateT.isEmpty()) {
                ptm.setString(index++, dateF);
                ptm.setString(index++, dateT);
            }
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrder_id(rs.getString(1));
                o.setUser_id(rs.getString(2));
                o.setUser_name(rs.getString(3));
                o.setOrder_date(rs.getString(4));
                o.setAddress(rs.getString(5));
                o.setOrder_total(rs.getString(6));
                o.setPayment(rs.getString(7));
                o.setOrder_status(rs.getString(8));
                listO.add(o);
            }
            return listO;
        } catch (SQLException ex) {
            System.out.println("getAllOrder: " + ex.getMessage());
        }
        return null;
    }

    public Order getOrderByOrderID(String oid) {
        String sql = "SELECT * FROM onlineshop.orders where order_id = ?";
        try {
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, oid);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrder_id(rs.getString(1));
                o.setOrder_date(rs.getString(3));
                o.setAddress(rs.getString(5));
                o.setPhone(rs.getString(6));
                o.setOrder_total(rs.getString(7));
                o.setPayment(rs.getString(9));
                o.setOrder_status(rs.getString(11));
                return o;
            }

        } catch (SQLException ex) {
            System.out.println("getOrderByOrderID: " + ex.getMessage());
        }
        return null;
    }

    public List<OrderDetail> getOrderDetailByOrderID(String oid) {
        try {
            List<OrderDetail> list = new ArrayList<>();
            String sql = "select p.product_id,p.product_name,s.size,p.img,o.quantity,o.price,o.product_size_id,\n"
                    + "o.status_feedback from  orderdetail o left join product_size ps on o.product_size_id=ps.product_size_id left join\n"
                    + "product p on ps.product_id=p.product_id left join size s on ps.size_id=s.size_id\n"
                    + "where o.order_id = ?;";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, oid);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setProduct_id(rs.getString(1));
                o.setProduct_name(rs.getString(2));
                o.setSize(rs.getString(3));
                o.setImg(rs.getString(4));
                o.setQuantity(rs.getString(5));
                o.setPrice(rs.getString(6));
                o.setProductSizeId(rs.getString(7));
                o.setStatus_feedback(rs.getString(8));
                list.add(o);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("getOrderDetailByOrderID: " + ex.getMessage());
        }
        return null;
    }

    public void UpdateOrderStatusFeedback(String statusFB, String oid) {
        try {
            String sql = "update orderdetail\n"
                    + " set status_feedback = ?\n"
                    + " where order_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, statusFB);
            
            ptm.setString(2, oid);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UpdateOrderStatusFeedback: " + ex.getMessage());
        }
    }

    public void UpdateOrderStatus(String os, String oid) {
        try {
            String sql = "update orders\n"
                    + "set status = ?\n"
                    + "where order_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, os);
            ptm.setString(2, oid);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UpdateOrderStatus: " + ex.getMessage());
        }
    }

    public void UpdateQuantityForProductSize(String quantity, String psid) {
        try {
            String sql = "update product_size\n"
                    + "set quantity = quantity + ?\n"
                    + "where product_size_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, quantity);
            ptm.setString(2, psid);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UpdateOrderStatus: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        OrderDAO od = new OrderDAO();
        od.UpdateQuantityForProductSize("2", "111");
    }
}
