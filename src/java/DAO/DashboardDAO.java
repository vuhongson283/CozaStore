/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TTT
 */
public class DashboardDAO extends DBContext {

    public List<String> getYearFilters() {
        try {
            String sql = "SELECT order_date FROM onlineshop.orders";
            List<String> list = new ArrayList<>();
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String year = rs.getString(1).substring(0, 4);
                list.add(year);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getYearFilters: " + e.getMessage());
        }
        return null;
    }

    public List<String> getOrCompletedByYear() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT count(order_date), year(order_date) FROM onlineshop.orders where status = 3 group by year(order_date) limit 5";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getOrCompletedByYear: " + e.getMessage());
        }
        return null;
    }

    public List<String> getOrToPay() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT count(order_date), year(order_date) FROM onlineshop.orders where status = 4 group by year(order_date) limit 5";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getOrToPay: " + e.getMessage());
        }
        return null;
    }

    public List<String> getYear() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT year(order_date) FROM onlineshop.orders group by year(order_date) order by year(order_date) asc limit 5";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getYear: " + e.getMessage());
        }
        return null;
    }
    
    public List<String> getOrByQuarter(String year, int a, int b, int c) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT count(order_date), year(order_date), month(order_date) FROM onlineshop.orders where status = ? and year(order_date) = ? and month(order_date) between ? and ? group by year(order_date), month(order_date) order by month(order_date) asc";
            Connection con = getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(2, Integer.parseInt(year));
            ptm.setInt(3, a);
            ptm.setInt(4, b);
            ptm.setInt(1, c);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getOrByQuarter: " + e.getMessage());
        }
        return null;
    }

    public int getTotalOrder() {
        try {
            int totalOrder = 0;
            String sql = "SELECT count(order_id) FROM onlineshop.orders;";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                totalOrder = rs.getInt(1);
            }
            return totalOrder;
        } catch (Exception e) {
            System.out.println("getTotalOrder: " + e.getMessage());
        }
        return 0;
    }

    public int getOrderCancelled() {
        try {
            int orderCancelled = 0;
            String sql = "SELECT count(order_id) FROM onlineshop.orders where status = 4";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                orderCancelled = rs.getInt(1);
            }
            return orderCancelled;
        } catch (Exception e) {
            System.out.println("getOrderCancelled: " + e.getMessage());
        }
        return 0;
    }

    public double getOrderProfit() {
        try {
            double orderProfit = 0;
            String sql = "SELECT sum(total) FROM onlineshop.orders where status = 3";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                orderProfit = rs.getDouble(1);
            }
            return (double) Math.round((orderProfit * 100) / 100);
        } catch (Exception e) {
            System.out.println("getOrderProfit: " + e.getMessage());
        }
        return 0;
    }

    public int getFeedbackHighly() {
        try {
            int fbHighly = 0;
            String sql = "SELECT count(feedback_id) FROM onlineshop.feedback where feedback_rate >= 3";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                fbHighly = rs.getInt(1);
            }
            return fbHighly;
        } catch (Exception e) {
            System.out.println("getFeedbackHighly: " + e.getMessage());
        }
        return 0;
    }

    public List<String> getProSoldByYear() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "select sum(quantity) ,year(order_date) from onlineshop.orderdetail od join orders o on od.order_id = o.order_id group by year(order_date) order by year(order_date) asc  limit 5";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getProSoldByYear: " + e.getMessage());
        }
        return null;
    }

    public List<String> getProStock() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT sum(total), year(order_date) FROM onlineshop.orders where status = 3 group by year(order_date)";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                double orderProfit =  Math.round((rs.getDouble(1) * 100) / 100);
                list.add(orderProfit + "");
            }
            return list;
        } catch (Exception e) {
            System.out.println("getProStock: " + e.getMessage());
        }
        return null;
    }
    public List<String> getRevenueQuarter(String year, int a, int b) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT sum(total), year(order_date), month(order_date) FROM onlineshop.orders where status = 3 and year(order_date) = ? and month(order_date) between ? and ? group by year(order_date), month(order_date) order by month(order_date) asc";
            Connection con = getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(2, a);
            ptm.setInt(3, b);
            ptm.setInt(1, Integer.parseInt(year));
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                double orderProfit =  Math.round((rs.getDouble(1) * 100) / 100);
                list.add(orderProfit + "");
            }
            return list;
        } catch (Exception e) {
            System.out.println("getRevenueQuarter: " + e.getMessage());
        }
        return null;
    }
    public List<String> getProSoldQuarter(String year, int a, int b) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "SELECT sum(quantity) ,year(order_date), month(order_date) FROM onlineshop.orderdetail od join orders o on od.order_id = o.order_id where status = 3 and year(order_date) = ? and month(order_date) between ? and ? group by year(order_date), month(order_date) order by month(order_date) asc";
            Connection con = getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(2, a);
            ptm.setInt(3, b);
            ptm.setInt(1, Integer.parseInt(year));
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getProSoldQuarter: " + e.getMessage());
        }
        return null;
    }

    public String getBestSeller() {
        try {
            String bs = "";
            String sql = "select count(p.product_id), product_name from product_size ps join orderdetail o on o.product_size_id = ps.product_size_id \n" +
"	join product p on ps.product_id = p.product_id group by p.product_id order by count(p.product_id) desc limit 1";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                bs = rs.getString(2);
            }
            return bs;
        } catch (Exception e) {
            System.out.println("getBestSeller: " + e.getMessage());
        }
        return null;
    }

    public int getTotalCus() {
        try {
            int ts = 0;
            String sql = "SELECT count(user_id) FROM onlineshop.users where status = 1 and role_id = 4";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                ts = rs.getInt(1);
            }
            return ts;
        } catch (Exception e) {
            System.out.println("getTotalCus: " + e.getMessage());
        }
        return 0;
    }

    public int getNewPro() {
        try {
            int np = 0;
            String sql = "select count(product_id) from product where year(create_date) = 2024 and month(create_date) = 2";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                np = rs.getInt(1);
            }
            return np;
        } catch (Exception e) {
            System.out.println("getNewPro: " + e.getMessage());
        }
        return 0;
    }

    public int getNewBlog() {
        try {
            int nb = 0;
            String sql = "select count(blog_id) from blog where status = 1 and year(updated_date) = 2024 and month(updated_date) = 3";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                nb = rs.getInt(1);
            }
            return nb;
        } catch (Exception e) {
            System.out.println("getNewBlog: " + e.getMessage());
        }
        return 0;
    }

    public int getTotalUsers() {
        try {
            int tu = 0;
            String sql = "SELECT count(user_id) FROM onlineshop.users where status = 1";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                tu = rs.getInt(1);
            }
            return tu;
        } catch (Exception e) {
            System.out.println("getTotalUsers: " + e.getMessage());
        }
        return 0;
    }
    
}
