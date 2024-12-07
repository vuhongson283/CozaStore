/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Category;
import Model.Product;
import Model.ProductSize;
import Model.Supplier;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getAllProductByFilter(String paramSearch, String categorySearch, String statusSearch) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Product> products = new ArrayList<>();
        ArrayList<Integer> productIdFilter = getProductIdFilter(paramSearch, categorySearch, statusSearch);
        Connection c = getConnection();
        for (Integer i : productIdFilter) {
            String sql = "SELECT p.product_id, p.product_name, p.price, p.category_id, p.supplier_id, p.img, p.description, p.status, p.sale_price, ps.size_id, ps.quantity FROM product p "
                    + "LEFT JOIN product_size ps ON p.product_id = ps.product_id "
                    + "WHERE "
                    + "p.category_id LIKE ? "
                    + "AND p.status LIKE ? "
                    + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                    + "AND p.product_id = ?";
            PreparedStatement ptm = c.prepareStatement(sql);
            if (categorySearch.isEmpty()) {
                ptm.setString(1, "%" + categorySearch + "%");
            } else {
                ptm.setString(1, "%" + String.valueOf(categoryDAO.getCategoryId(categorySearch)) + "%");
            }
            ptm.setString(2, "%" + statusSearch + "%");
            ptm.setString(3, "%" + paramSearch + "%");
            ptm.setString(4, "%" + paramSearch + "%");
            ptm.setInt(5, i);
            ResultSet rs = ptm.executeQuery();
            ArrayList<ProductSize> productSizeList = new ArrayList<>();
            Product product = new Product();
            while (rs.next()) {
                ProductSize productSize = new ProductSize();
                productSize.setProductId(String.valueOf(rs.getInt("product_id")));
                productSize.setSizeId(String.valueOf(rs.getInt("size_id")));
                productSize.setStatus(String.valueOf(rs.getBoolean("status")));
                productSize.setQuantity(String.valueOf(rs.getInt("quantity")));
                productSizeList.add(productSize);
                product.setProductSizes(productSizeList);
                product.setProduct_id(String.valueOf(rs.getInt("product_id")));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(String.valueOf(rs.getFloat("price")));
                product.setProduct_saleprice(String.valueOf(rs.getFloat("sale_price")));
                product.setCategory_id(String.valueOf(rs.getInt("category_id")));
                product.setSupplier_id(String.valueOf(rs.getInt("supplier_id")));
                product.setDes(rs.getString("description"));
                product.setStatus(String.valueOf(rs.getBoolean("status")));
            }
            products.add(product);
        }
        return (ArrayList<Product>) products;
    }

    public Product getProductByID(String id) {
        try {

            String sql = "SELECT `product`.`product_name`,\n"
                    + "    `product`.`price`,\n"
                    + "    `product`.`category_id`,\n"
                    + "    `product`.`supplier_id`,\n"
                    + "    `product`.`img`,\n"
                    + "    `product`.`description`,\n"
                    + "    `product`.`create_date`,\n"
                    + "    `product`.`status`,\n"
                    + "    `product`.`sale_price`,`product`.`product_id`\n"
                    + "FROM `onlineshop`.`product` where product_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, id);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_name(rs.getString(1));
                p.setProduct_price(rs.getString(2));
                p.setCategory_id(rs.getString(3));
                p.setSupplier_id(rs.getString(4));
                p.setImg(rs.getString(5));
                p.setDes(rs.getString(6));
                p.setCreate_date(rs.getString(7));
                p.setStatus(rs.getString(8));
                p.setProduct_saleprice(rs.getString(9));
                p.setProduct_id(rs.getString(10));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> get5RelatedProduct(String pid, String category_id) {
        try {
            String sql = "SELECT * FROM onlineshop.product\n"
                    + "where product_id != ? and category_id = ? and status = 1 limit 5 ";
            List<Product> list = new ArrayList<>();
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, pid);
            ptm.setString(2, category_id);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getString(1));
                p.setProduct_name(rs.getString(2));
                p.setProduct_price(rs.getString(3));
                p.setCategory_id(rs.getString(4));
                p.setSupplier_id(rs.getString(5));
                p.setImg(rs.getString(6));
                p.setDes(rs.getString(7));
                p.setCreate_date(rs.getString(8));
                p.setStatus(rs.getString(9));
                p.setProduct_saleprice(rs.getString(10));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("get5RelatedProduct: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Product> getProductFilter(String paramSearch, String categorySearch, String statusSearch, int page) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Product> products = new ArrayList<>();
        ArrayList<Integer> productIdFilter = getProductIdFilterAndPage(paramSearch, categorySearch, statusSearch, page);
        Connection c = getConnection();
        for (int i : productIdFilter) {
            String sql = "SELECT p.product_id, p.product_name, p.price, p.category_id, p.supplier_id, p.img, p.description, p.status, p.sale_price, ps.size_id, ps.quantity FROM product p "
                    + "LEFT JOIN product_size ps ON p.product_id = ps.product_id "
                    + "WHERE "
                    + "p.category_id LIKE ? "
                    + "AND p.status LIKE ? "
                    + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                    + "AND p.product_id = ? ";
            PreparedStatement ptm = c.prepareStatement(sql);
            if (categorySearch.isEmpty()) {
                ptm.setString(1, "%" + categorySearch + "%");
            } else {
                ptm.setString(1, "%" + String.valueOf(categoryDAO.getCategoryId(categorySearch)) + "%");
            }
            ptm.setString(2, "%" + statusSearch + "%");
            ptm.setString(3, "%" + paramSearch + "%");
            ptm.setString(4, "%" + paramSearch + "%");
            ptm.setInt(5, i);
            ResultSet rs = ptm.executeQuery();
            ArrayList<ProductSize> productSizeList = new ArrayList<>();
            Product product = new Product();
            while (rs.next()) {
                ProductSize productSize = new ProductSize();
                productSize.setProductId(String.valueOf(rs.getInt("product_id")));
                productSize.setSizeId(String.valueOf(rs.getInt("size_id")));
                productSize.setStatus(String.valueOf(rs.getBoolean("status")));
                productSize.setQuantity(String.valueOf(rs.getInt("quantity")));
                productSizeList.add(productSize);
                product.setProductSizes(productSizeList);
                product.setProduct_id(String.valueOf(rs.getInt("product_id")));
                product.setImg(rs.getString("img"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(String.valueOf(rs.getFloat("price")));
                product.setProduct_saleprice(String.valueOf(rs.getFloat("sale_price")));
                product.setCategory_id(String.valueOf(rs.getInt("category_id")));
                product.setSupplier_id(String.valueOf(rs.getInt("supplier_id")));
                product.setDes(rs.getString("description"));
                product.setStatus(String.valueOf(rs.getBoolean("status")));
            }
            products.add(product);
        }
        return (ArrayList<Product>) products;
    }

    public void addNewProduct(String productName, float productPrice, float productSale, int productCategoryId, String productSupplierId, String productFeature, String avatar) throws Exception {
        Connection c = getConnection();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Convert to SQL timestamp
        Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);

        String sql = "INSERT INTO product (product_name, price, category_id, supplier_id, img, description, create_date, status, sale_price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = c.prepareStatement(sql);

        // Set parameter values
        preparedStatement.setString(1, productName);
        preparedStatement.setFloat(2, productPrice);
        preparedStatement.setInt(3, productCategoryId);
        preparedStatement.setInt(4, Integer.parseInt(productSupplierId));
        preparedStatement.setString(5, avatar);
        preparedStatement.setString(6, productFeature);
        preparedStatement.setTimestamp(7, sqlTimestamp); // Use Timestamp for date and time
        preparedStatement.setBoolean(8, true);
        preparedStatement.setDouble(9, productSale);

        // Execute the INSERT query
        preparedStatement.executeUpdate();

        // Close resources (Connection and PreparedStatement)
        preparedStatement.close();
        c.close();
    }

    private ArrayList<Integer> getProductIdFilter(String paramSearch, String categorySearch, String statusSearch) throws SQLException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Integer> productIds = new ArrayList<>();
        String sql = "SELECT * FROM onlineshop.product p "
                + "WHERE "
                + "    p.category_id LIKE ? "
                + "    AND p.status LIKE ? "
                + "    AND (p.product_name LIKE ? OR p.description LIKE ?)";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        if (categorySearch.isEmpty()) {
            ptm.setString(1, "%" + categorySearch + "%");
        } else {
            ptm.setString(1, "%" + String.valueOf(categoryDAO.getCategoryId(categorySearch)) + "%");
        }
        ptm.setString(2, "%" + statusSearch + "%");
        ptm.setString(3, "%" + paramSearch + "%");
        ptm.setString(4, "%" + paramSearch + "%");
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            productIds.add(rs.getInt("product_id"));
        }
        return productIds;
    }

    private ArrayList<Integer> getProductIdFilterAndPage(String paramSearch, String categorySearch, String statusSearch, int page) throws SQLException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Integer> productIds = new ArrayList<>();
        Connection c = getConnection();
        String sql = "SELECT * FROM onlineshop.product p "
                + "WHERE "
                + "    p.category_id LIKE ? "
                + "    AND p.status LIKE ? "
                + "    AND (p.product_name LIKE ? OR p.description LIKE ?)" + "ORDER BY "
                + "    p.product_id DESC "
                + "LIMIT 10 OFFSET ?";
        PreparedStatement ptm = c.prepareStatement(sql);
        if (categorySearch.isEmpty()) {
            ptm.setString(1, "%" + categorySearch + "%");
        } else {
            ptm.setString(1, "%" + String.valueOf(categoryDAO.getCategoryId(categorySearch)) + "%");
        }
        ptm.setString(2, "%" + statusSearch + "%");
        ptm.setString(3, "%" + paramSearch + "%");
        ptm.setString(4, "%" + paramSearch + "%");
        ptm.setInt(5, (page - 1) * 10);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            productIds.add(rs.getInt("product_id"));
        }
        return productIds;
    }

    public int getLastProductId() throws SQLException {
        String sql = "SELECT product_id FROM onlineshop.product "
                + "ORDER BY product_id;";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        int lastProductId = 0;
        while (rs.next()) {
            lastProductId = rs.getInt("product_id");
        }
        return lastProductId;
    }

    public ArrayList<ProductSize> getProductSize(String id) throws SQLException {
        ArrayList<ProductSize> productSizes = new ArrayList<>();
        String sql = "SELECT ps.size_id, s.size, ps.product_id, ps.quantity, ps.status FROM product_size ps\n"
                + "INNER JOIN Size s ON s.size_id = ps.size_id\n"
                + "WHERE product_id = ?";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ptm.setString(1, id);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            String sizeId = rs.getString("size_id");
            String productId = rs.getString("product_id");
            String quantity = rs.getString("quantity");
            String status = rs.getString("status");
            ProductSize productSize = new ProductSize(sizeId, productId, quantity, status);
            productSizes.add(productSize);
        }
        return productSizes;
    }

    public void updateProductStatus() throws SQLException {
        String sql = "UPDATE product p \n"
                + "INNER JOIN category c ON c.category_id = p.category_id \n"
                + "INNER JOIN supplier s ON s.supplier_id = p.supplier_id  \n"
                + "SET p.status = CASE WHEN c.status = 0 OR s.status = 0 THEN 0 ELSE 1 END;";
        Connection connection = getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.executeUpdate();
    }
}
