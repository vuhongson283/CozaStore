/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.BlogCategory;
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
public class BlogCategoryDAO extends DBContext {

    public ArrayList<BlogCategory> getAllBlogCategory() throws SQLException {
        ArrayList<BlogCategory> blogCategorys = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT * FROM onlineshop.categoryblog WHERE status = 1;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
            blogCategory.setCategoryBlogName(rs.getString("categoryBlog_name"));
            blogCategorys.add(blogCategory);
        }
        return blogCategorys;
    }

    public List<BlogCategory> getAllBlogCategory1() {
        try {
            List<BlogCategory> blogCategorys = new ArrayList<>();
            Connection connection = getConnection();
            String sql = "SELECT * FROM onlineshop.categoryblog WHERE status = 1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogCategory blogCategory = new BlogCategory();
                blogCategory.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
                blogCategory.setCategoryBlogName(rs.getString("categoryBlog_name"));

                blogCategorys.add(blogCategory);
            }
            return blogCategorys;
        } catch (Exception e) {
            System.out.println("getAllBlogCategory1: " + e.getMessage());
        }
        return null;
    }

    public List<BlogCategory> getBlogCategory12() {
        try {
            List<BlogCategory> blogCategorys = new ArrayList<>();
            Connection connection = getConnection();
            String sql = "select * from categoryblog where status = 1 limit 2;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogCategory blogCategory = new BlogCategory();
                blogCategory.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
                blogCategory.setCategoryBlogName(rs.getString("categoryBlog_name"));
                blogCategory.setCategoryBlogImg(rs.getString("categoryBlog_img"));
                blogCategorys.add(blogCategory);
            }
            return blogCategorys;
        } catch (Exception e) {
            System.out.println("getAllBlogCategory1: " + e.getMessage());
        }
        return null;
    }

    public List<BlogCategory> getBlogCategory35() {
        try {
            List<BlogCategory> blogCategorys = new ArrayList<>();
            Connection connection = getConnection();
            String sql = "select * from categoryblog where status = 1 limit 2,18446744073709551615;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogCategory blogCategory = new BlogCategory();
                blogCategory.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
                blogCategory.setCategoryBlogName(rs.getString("categoryBlog_name"));
                blogCategory.setCategoryBlogImg(rs.getString("categoryBlog_img"));
                blogCategorys.add(blogCategory);
            }
            return blogCategorys;
        } catch (Exception e) {
            System.out.println("getAllBlogCategory1: " + e.getMessage());
        }
        return null;
    }
}
