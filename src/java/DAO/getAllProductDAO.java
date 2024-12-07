/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.MD5;

import Model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class getAllProductDAO {

    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * From Product where status=1 order by product_id desc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getNew16Product() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where status=1 order by product_id desc limit 16;";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getString("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Supplier> getAllBrand() {
        List<Supplier> list = new ArrayList<>();
        String sql = "select * from Supplier";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplier_id(rs.getString("supplier_id"));
                supplier.setSupplier_name(rs.getString("supplier_name"));
                list.add(supplier);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByCategoryID(String category_id) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where category_id =?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, category_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                Category category = new Category();
                category.setCategory_id(rs.getString("category_id"));

                Supplier supplier = new Supplier();
                supplier.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));
                product.setProductSizes((ArrayList<ProductSize>) rs.getArray("size"));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public Product getProductByID(String product_id) {
        Product product = new Product();

        String sql = "select * from product where product_id =?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));
                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));
                product.setProductSizes((ArrayList<ProductSize>) rs.getArray("size"));
            }
        } catch (Exception e) {
        }
        return product;
    }

   public void editProduct(String pId, String product_name, String price, String salePrice, String category_id,
            String supplier_id, String img, String description, String status) {
        String sql = "UPDATE product SET product_name = ?, price = ?, category_id = ?, supplier_id = ?, img = ?, description = ?, status = ?, sale_price = ? WHERE product_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, product_name);
            ps.setString(2, price);
            ps.setString(3, category_id);
            ps.setString(4, supplier_id);
            ps.setString(5, img);
            ps.setString(6, description);
            ps.setString(7, status);
            ps.setString(8, salePrice);
            ps.setString(9, pId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Print the exception for debugging purposes
        } finally {
            // Close resources (Connection and PreparedStatement)
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Handle the exception appropriately
            }
        }
    }
   public void editProductWithoutImg(String pId, String product_name, String price, String salePrice, String category_id,
            String supplier_id,  String description, String status) {
        String sql = "UPDATE product SET product_name = ?, price = ?, category_id = ?, supplier_id = ?, description = ?, status = ?, sale_price = ? WHERE product_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, product_name);
            ps.setString(2, price);
            ps.setString(3, category_id);
            ps.setString(4, supplier_id);
            ps.setString(5, description);
            ps.setString(6, status);
            ps.setString(7, salePrice);
            ps.setString(8, pId);
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Print the exception for debugging purposes
        } finally {
            // Close resources (Connection and PreparedStatement)
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Handle the exception appropriately
            }
        }
    }

    public List<Product> getProductBySearch(String txtSearch) {
        List<Product> list = new ArrayList<>();

        String query = "select * from product where product_name like ? and status = 1 order by product_id desc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getProductSortDefault(String supplier, String category, String min, String max) {
        List<Product> list = new ArrayList<>();

        String query = "select * from product where supplier_id like ? and category_id like ? and price between ? and ?  and status=1";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + supplier + "%");
            ps.setString(2, "%" + category + "%");
            ps.setString(3, min);
            ps.setString(4, max);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getProductSortNew(String supplier, String category, String min, String max) {
        List<Product> list = new ArrayList<>();

        String query = "select * from product where supplier_id like ? and category_id like ? and price between ? and ? and status=1 order by product_id desc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + supplier + "%");
            ps.setString(2, "%" + category + "%");
            ps.setString(3, min);
            ps.setString(4, max);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getProductSortHighToLow(String supplier, String category, String min, String max) {
        List<Product> list = new ArrayList<>();

        String query = "select * from product where supplier_id like ? and category_id like ? and price between ? and ? and status=1 order by price desc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + supplier + "%");
            ps.setString(2, "%" + category + "%");
            ps.setString(3, min);
            ps.setString(4, max);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getProductSortLowToHigh(String supplier, String category, String min, String max) {
        List<Product> list = new ArrayList<>();

        String query = "select * from product where supplier_id like ? and category_id like ? and price between ? and ? and status=1 order by price asc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + supplier + "%");
            ps.setString(2, "%" + category + "%");
            ps.setString(3, min);
            ps.setString(4, max);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }
    public List<Product> getProductSortPopular(String supplier, String category, String min, String max){
        List<Product> list = new ArrayList<>();

        String query = "select SUM(o.quantity),p.* from product p left join product_size ps on p.product_id=ps.product_id "
                + "left join orderdetail o on o.product_size_id=ps.product_size_id "
                + "where p.supplier_id like ? and p.category_id like ? and p.price between ? and ? and p.status=1 "
                + "group by p.product_id order by SUM(o.quantity) desc";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, "%" + supplier + "%");
            ps.setString(2, "%" + category + "%");
            ps.setString(3, min);
            ps.setString(4, max);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString("product_id"));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("price"));
                product.setProduct_saleprice(rs.getString("sale_price"));
                product.setCategory_id(rs.getString("category_id"));
                product.setSupplier_id(rs.getString("supplier_id"));

                product.setDes(rs.getString("description"));
                product.setStatus(rs.getString("status"));
                product.setCreate_date(rs.getString("create_date"));

                list.add(product);
            }
        } catch (Exception e) {

        }
        return list;
    }
}
