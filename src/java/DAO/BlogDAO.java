/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Blog;
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
public class BlogDAO extends DBContext {

    public ArrayList<Blog> getAllBlogByFilter(String paramSearch, String categorySearch) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT "
                + "b.blog_id, "
                + "b.title, "
                + "a.author_name, "
                + "b.updated_date, "
                + "b.content, "
                + "b.thumbnail, "
                + "b.brief_infor, "
                + "b.categoryBlog_id, "
                + "b.status "
                + "FROM "
                + "blog b "
                + "INNER JOIN author a ON a.author_id = b.author_id "
                + "WHERE "
                + "(b.content LIKE ? "
                + "OR b.title LIKE ? )AND b.categoryBlog_id LIKE ? AND b.status = 1 ";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + paramSearch + "%");
        stm.setString(2, "%" + paramSearch + "%");
        stm.setString(3, "%" + categorySearch + "%");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Blog blog = new Blog();
            blog.setBlogId(String.valueOf(rs.getInt("blog_id")));
            blog.setTitle(rs.getString("title"));
            blog.setAuthorName(rs.getString("author_name"));
            blog.setUpdatedDate(String.valueOf(rs.getDate("updated_date")));
            blog.setContent(rs.getString("content"));
            blog.setThumbnail(rs.getString("thumbnail"));
            blog.setBriefInfo(rs.getString("brief_infor"));
            blog.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
            blog.setStatus(String.valueOf(rs.getBoolean("status")));
            blogs.add(blog);
        }
        return blogs;
    }

    public ArrayList<Blog> getBlogFilterPaging(String paramSearch, String categorySearch, int page) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT "
                + "b.blog_id, "
                + "b.title, "
                + "a.author_name, "
                + "b.updated_date, "
                + "b.content, "
                + "b.thumbnail, "
                + "b.brief_infor, "
                + "b.categoryBlog_id, "
                + "b.status "
                + "FROM "
                + "blog b "
                + "INNER JOIN author a ON a.author_id = b.author_id "
                + "WHERE "
                + "(b.content LIKE ? "
                + "OR b.title LIKE ? )"
                + "AND b.categoryBlog_id LIKE ? AND b.status = 1 ORDER BY b.updated_date DESC  "
                + "LIMIT 3 OFFSET ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + paramSearch + "%");
        stm.setString(2, "%" + paramSearch + "%");
        stm.setString(3, "%" + categorySearch + "%");
        stm.setInt(4, (page - 1) * 3);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Blog blog = new Blog();
            blog.setBlogId(String.valueOf(rs.getInt("blog_id")));
            blog.setTitle(rs.getString("title"));
            blog.setAuthorName(rs.getString("author_name"));
            blog.setUpdatedDate(String.valueOf(rs.getDate("updated_date")));
            blog.setContent(rs.getString("content"));
            blog.setThumbnail(rs.getString("thumbnail"));
            blog.setBriefInfo(rs.getString("brief_infor"));
            blog.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
            blog.setStatus(String.valueOf(rs.getBoolean("status")));
            blogs.add(blog);
        }
        return blogs;
    }

    public Blog getBlogWithId(String blogId) throws SQLException {
        Connection connection = getConnection();
        String sql
                = "SELECT "
                + "    b.blog_id, "
                + "    b.title, "
                + "    a.author_name, "
                + "    b.updated_date, "
                + "    b.content, "
                + "    b.thumbnail, "
                + "    b.brief_infor, "
                + "    b.categoryBlog_id, "
                + "    b.status "
                + "FROM "
                + "    blog b "
                + "INNER JOIN author a ON a.author_id = b.author_id "
                + "WHERE b.blog_id LIKE ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + blogId + "%");
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Blog blog = new Blog();
            blog.setBlogId(String.valueOf(rs.getInt("blog_id")));
            blog.setTitle(rs.getString("title"));
            blog.setAuthorName(rs.getString("author_name"));
            blog.setUpdatedDate(String.valueOf(rs.getDate("updated_date")));
            blog.setContent(rs.getString("content"));
            blog.setThumbnail(rs.getString("thumbnail"));
            blog.setBriefInfo(rs.getString("brief_infor"));
            blog.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
            blog.setStatus(String.valueOf(rs.getBoolean("status")));
            return blog;
        }
        return null;
    }

    public List<Blog> getBlogByCon(String pl_content, String pl_author, String pl_title, String pl_status) {
        try {
            List<Blog> list = new ArrayList<>();
            String sql = "select * from blog b join author a on b.author_id = a.author_id where author_name like ? and title like ? and content like ? and status like ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + pl_author + "%");
            ptm.setString(2, "%" + pl_title + "%");
            ptm.setString(3, "%" + pl_content + "%");
            ptm.setString(4, "%" + pl_status + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String author = rs.getString(11);
                String title = rs.getString(2);
                String content = rs.getString(5);
                String status = rs.getString(9);
                String updated_date = rs.getString(4);
                String blog_id = rs.getString(1);
                Blog b = new Blog(blog_id, title, author, updated_date, content, "", "", "", status);
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getBlogByCon: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Blog> getAllBlog() {
        try {
            ArrayList<Blog> blogs = new ArrayList<>();
            Connection connection = getConnection();
            String sql = "SELECT "
                    + "b.blog_id, "
                    + "b.title, "
                    + "a.author_name, "
                    + "b.updated_date, "
                    + "b.content, "
                    + "b.thumbnail, "
                    + "b.brief_infor, "
                    + "b.categoryBlog_id, "
                    + "b.status "
                    + "FROM "
                    + "blog b "
                    + "INNER JOIN author a ON a.author_id = b.author_id ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(String.valueOf(rs.getInt("blog_id")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthorName(rs.getString("author_name"));
                blog.setUpdatedDate(String.valueOf(rs.getDate("updated_date")));
                blog.setContent(rs.getString("content"));
                blog.setThumbnail(rs.getString("thumbnail"));
                blog.setBriefInfo(rs.getString("brief_infor"));
                blog.setCategoryBlogId(String.valueOf(rs.getInt("categoryBlog_id")));
                blog.setStatus(rs.getString("status"));
                blogs.add(blog);
            }
            return blogs;
        } catch (Exception e) {
            System.out.println("getAllBlog: " + e.getMessage());
        }
        return null;
    }

    public void banBlog(String blog_id) {
        String query = "update blog set status=0 where blog_id=?";
        try {
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(query);
            ptm.setString(1, blog_id);

            ptm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void unbanBlog(String blog_id) {
        String query = "update blog set status=1 where blog_id=?";
        try {
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(query);
            ptm.setString(1, blog_id);
            ptm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addPost(String blog_thumbnail, String b_authorName, String b_updateDate, String blog_title, String blogCategories, String blog_content, String blog_brief) {
        try {
            String sql = "insert into blog ( title, author_id, updated_date, content, thumbnail, brief_infor, categoryBlog_id, status) values (?,1,?,?,?,?,?,1)";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, blog_title);
            ptm.setString(2, b_updateDate);
            ptm.setString(3, blog_content);
            ptm.setString(4, blog_thumbnail);
            ptm.setInt(6, Integer.parseInt(blogCategories));
            ptm.setString(5, blog_brief);
            ptm.executeUpdate();

        } catch (Exception e) {
            System.out.println("addPost: " + e.getMessage());
        }
    }

    public Blog getBlogById(String pl_blogId) {
        try {
            Blog b = new Blog();
            String sql = "select * from blog b join author a on b.author_id = a.author_id where blog_id like ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + pl_blogId + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String author = rs.getString(11);
                String title = rs.getString(2);
                String content = rs.getString(5);
                String status = rs.getString(9);
                String updated_date = rs.getString(4);
                String blog_id = rs.getString(1);
                String thumbnail = rs.getString(6);
                String brief_info = rs.getString(7);
                String categoryBlogId = String.valueOf(rs.getInt(8));
                b = new Blog(blog_id, title, author, updated_date, content, thumbnail, brief_info, categoryBlogId, status);

            }
            return b;
        } catch (Exception e) {
            System.out.println("getBlogByCon: " + e.getMessage());
        }
        return null;
    }

    public void deleteBlogById(String blog_id) {
        try {
            String sql = "delete from blog where blog_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setInt(1, Integer.parseInt(blog_id));
            ptm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteBlogById: " + e.getMessage());
        }
    }

    public void updateBlogStatus() throws SQLException {
        String sql = "UPDATE blog b \n"
                + "INNER JOIN categoryBlog cb ON b.categoryBlog_id = cb.categoryBlog_id SET b.status = CASE WHEN cb.status = 0 then 0 ELSE 1 END;";
        Connection connection = getConnection();
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
        }
    }

    public void updatePost(String blog_thumbnail1, String b_authorName, String b_updateDate, String blog_title, String blogCategories, String blog_content, String blog_brief, String blog_id) {
        try {
            String sql = "update blog set title = ?, updated_date = ?, content = ?, thumbnail = ?, brief_infor = ?, categoryBlog_id = ?  where blog_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, blog_title);
            ptm.setString(2, b_updateDate);
            ptm.setString(3, blog_content);
            ptm.setString(4, blog_thumbnail1);
            ptm.setInt(6, Integer.parseInt(blogCategories));
            ptm.setString(5, blog_brief);
            ptm.setString(7, blog_id);
            ptm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePost: " + e.getMessage());
        }
    }
}
