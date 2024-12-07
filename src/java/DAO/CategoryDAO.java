/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getAllCategory() throws Exception {
        String sql = "SELECT category_id, "
                + "category_name "
                + "FROM Category";
        List<Category> categories = new ArrayList<>();
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            Category category = new Category();
            category.setCategory_id(String.valueOf(rs.getInt("category_id")));
            category.setCategory_name(rs.getString("category_name"));
            categories.add(category);
        }
        return (ArrayList<Category>) categories;
    }

    public int getCategoryId(String categorySearch) throws SQLException, IOException {
        String sql = "SELECT * FROM category c "
                + "WHERE c.category_name = ?";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ptm.setString(1, categorySearch);
        ResultSet rs = ptm.executeQuery();
        if (rs.next()) {
            return rs.getInt("category_id");
        }
        return 0;
    }

    public ArrayList<Category> getAllCategoryAddAndEdit() throws SQLException {
        String sql = "SELECT category_id, "
                + "category_name "
                + "FROM Category WHERE status =1";
        List<Category> categories = new ArrayList<>();
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            Category category = new Category();
            category.setCategory_id(String.valueOf(rs.getInt("category_id")));
            category.setCategory_name(rs.getString("category_name"));
            categories.add(category);
        }
        return (ArrayList<Category>) categories;
    }
}
