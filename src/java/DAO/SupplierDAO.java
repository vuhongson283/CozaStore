/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luu Bach
 */
public class SupplierDAO extends DBContext {

    public ArrayList<Supplier> getAllSupplier() throws Exception {
        String sql = "SELECT  supplier_id\n"
                + "      ,supplier_name\n"
                + "  FROM Supplier";
        List<Supplier> suppliers = new ArrayList<>();
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupplier_id(String.valueOf(rs.getInt("supplier_id")));
            supplier.setSupplier_name(rs.getString("supplier_name"));
            suppliers.add(supplier);
        }
        return (ArrayList<Supplier>) suppliers;
    }

    public String getSupplierId(String supplier) throws SQLException {
        String sql = "SELECT * FROM onlineshop.supplier "
                + "WHERE supplier_name = ?";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ptm.setString(1, supplier);
        ResultSet rs = ptm.executeQuery();
        if (rs.next()) {
            return rs.getString("supplier_id");
        }
        return null;
    }

    public ArrayList<Supplier> getAllSupplierAddAndEdit() throws SQLException {
        String sql = "SELECT  supplier_id\n"
                + "      ,supplier_name\n"
                + "  FROM Supplier WHERE status = 1";
        List<Supplier> suppliers = new ArrayList<>();
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupplier_id(String.valueOf(rs.getInt("supplier_id")));
            supplier.setSupplier_name(rs.getString("supplier_name"));
            suppliers.add(supplier);
        }
        return (ArrayList<Supplier>) suppliers;
    }

}
