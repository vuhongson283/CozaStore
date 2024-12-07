/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.ProductSize;
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
 * @author Luu Bach
 */
public class ProductSizeDAO extends DBContext {

    public List<ProductSize> getAllSize(String pid) {
        try {
            String sql = "SELECT * FROM onlineshop.product_size where product_id = ?;";
            List<ProductSize> list = new ArrayList<>();

            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, pid);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                ProductSize ps = new ProductSize();
                ps.setProductSizeId(rs.getString(1));
                ps.setSizeId(rs.getString(2));
                ps.setProductId(rs.getString(3));
                ps.setQuantity(rs.getString(4));
                ps.setStatus(rs.getString(5));
                list.add(ps);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void addProductSize(ArrayList<Integer> quantityList, int newProductId) throws SQLException {
        Connection c = null;
        PreparedStatement ptm = null;
        try {
            c = getConnection();
            for (int i = 0; i < quantityList.size(); i++) {
                String sql = "INSERT INTO product_size (product_id, size_id, quantity, status) "
                        + "VALUES (?, ?, ?, ?)";
                ptm = c.prepareStatement(sql);
                ptm.setInt(1, newProductId);
                ptm.setInt(2, i + 1);
                ptm.setInt(3, quantityList.get(i));
                ptm.setInt(4, 1);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public ProductSize getQuantity(String pid, String sid) {
        try {
            String sql = "SELECT * FROM onlineshop.product_size where product_id = ? and size_id = ?;";

            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, pid);
            ptm.setString(2, sid);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                ProductSize ps = new ProductSize();
                ps.setProductSizeId(rs.getString(1));
                ps.setSizeId(rs.getString(2));
                ps.setProductId(rs.getString(3));
                ps.setQuantity(rs.getString(4));
                ps.setStatus(rs.getString(5));
                return ps;
            }
        } catch (SQLException ex) {
            System.out.println("getQuantity: " + ex.getMessage());
        }
        return null;

    }

    public void editProductSize(ArrayList<Integer> quantityList, int newProductId) throws SQLException {
        Connection c = null;
        PreparedStatement ptm = null;
        try {
            c = getConnection();
            for (int i = 0; i < quantityList.size(); i++) {
                if (quantityList.get(i) == 0) {
                    continue;
                }
                String sql = "UPDATE product_size SET quantity = ? WHERE product_id = ? AND size_id = ?";
                ptm = c.prepareStatement(sql);
                ptm.setInt(1, quantityList.get(i));
                ptm.setInt(2, newProductId);
                ptm.setInt(3, i + 1);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

}
