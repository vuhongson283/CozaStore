/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import Model.Order;
import Model.OrderDetail;
import Model.Product;
import Model.ProductSize;
import Model.Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCMSI
 */
public class CartDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addToCart(String user_id, String size_id, String pid, String quantity) {
        String query = "insert into cartdetail (user_id,product_size_id,quantity) "
                + "values (?,(select ps.product_size_id from product_size ps left join product p on ps.product_id=p.product_id where ps.size_id=? and ps.product_id=? and p.status =1),?);";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, size_id);
            ps.setString(3, pid);
            ps.setString(4, quantity);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }
    public void addToWishList(String user_id, String pid) {
        String query ="insert into wishlist (user_id,product_id) values(?,?)" ;
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            
            ps.setString(2, pid);
            
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }
     public Wishlist checkWishList(String user_id, String pid) {

        String query = "select * from wishlist where user_id = ? and product_id = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, pid);
            

            rs = ps.executeQuery();
            while (rs.next()) {
                return new Wishlist(rs.getString(1),rs.getString(2));
            }
        } catch (Exception e) {

        }
        return null;
    }
    public List<Wishlist> getWishList(String user_id) {
        List<Wishlist> list = new ArrayList<>();
        String query = "select * from wishlist w left join product p on w.product_id=p.product_id where w.user_id=? and p.status=1";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Wishlist wl = new Wishlist();
                wl.setUid(rs.getString(1));
                wl.setPid(rs.getString(2));
                wl.setPname(rs.getString(4));
                wl.setPrice(rs.getString(5));
                wl.setCateid(rs.getString(6));
                wl.setSupid(rs.getString(7));
                wl.setImg(rs.getString(8));
                wl.setSaleprice(rs.getString(12));

                list.add(wl);
            }
        } catch (Exception e) {

        }
        return list;
    }
    public void removeItemWishList(String user_id, String pid) {
        String query ="delete from wishlist where user_id= ? and product_id=?" ;
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, pid);
            
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public List<Cart> getListCart(String user_id) {
        List<Cart> list = new ArrayList<>();
        String query = "select c.user_id, ps.product_size_id,ps.size_id,p.product_id, p.product_name, p.price, p.category_id, p.supplier_id, p.img, p.status, p.sale_price,c.quantity,ps.quantity as 'stockofquantity' from product p \n"
                + "left join product_size ps on p.product_id= ps.product_id \n"
                + "left join cartdetail c on c.product_size_id=ps.product_size_id \n"
                + "where c.user_id=? and p.status=1";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setUser_id(rs.getString("user_id"));
                cart.setProduct_size_id(rs.getString("product_size_id"));
                cart.setSize_id(rs.getString("size_id"));
                cart.setProduct_id(rs.getString("product_id"));
                cart.setProduct_name(rs.getString("product_name"));
                cart.setPrice(rs.getString("price"));
                cart.setCategory_id(rs.getString("category_id"));
                cart.setSupplier_id(rs.getString("supplier_id"));
                cart.setImg(rs.getString("img"));
                cart.setStatus(rs.getString("status"));
                cart.setSale_price(rs.getString("sale_price"));
                cart.setQuantity(rs.getString("quantity"));
                cart.setStockofquantity(rs.getString("stockofquantity"));

                list.add(cart);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Cart checkCart(String user_id, String pid, String sid) {

        String query = "select c.user_id, ps.product_size_id,ps.size_id,p.product_id, p.product_name, p.price, p.category_id, p.supplier_id, p.img, p.status, p.sale_price,c.quantity,ps.quantity as 'stockofquantity' from product p \n"
                + "               left join product_size ps on p.product_id= ps.product_id \n"
                + "               left join cartdetail c on c.product_size_id=ps.product_size_id \n"
                + "                where c.user_id=? and p.status=1 and ps.product_id=? and ps.size_id=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, pid);
            ps.setString(3, sid);

            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setUser_id(rs.getString("user_id"));
                cart.setProduct_size_id(rs.getString("product_size_id"));
                cart.setSize_id(rs.getString("size_id"));
                cart.setProduct_id(rs.getString("product_id"));
                cart.setProduct_name(rs.getString("product_name"));
                cart.setPrice(rs.getString("price"));
                cart.setCategory_id(rs.getString("category_id"));
                cart.setSupplier_id(rs.getString("supplier_id"));
                cart.setImg(rs.getString("img"));
                cart.setStatus(rs.getString("status"));
                cart.setSale_price(rs.getString("sale_price"));
                cart.setQuantity(rs.getString("quantity"));
                cart.setStockofquantity(rs.getString("stockofquantity"));

                return cart;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void updateQuantity(String user_id, String pid, String sid, String quantity) {
        String query = "update  cartdetail set quantity= ? where user_id=? and "
                + "product_size_id=( select ps.product_size_id from product_size ps left join product p on ps.product_id=p.product_id where ps.product_id=? and ps.size_id=? and p.status =1)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, quantity);
            ps.setString(2, user_id);
            ps.setString(3, pid);
            ps.setString(4, sid);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void removeItem(String user_id, String pid, String sid) {
        String query = "delete from cartdetail where user_id=? and product_size_id= (select ps.product_size_id from product_size ps left join product p on ps.product_id=p.product_id where ps.product_id=? and ps.size_id=? and p.status =1)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, pid);
            ps.setString(3, sid);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void addOrder(List<Cart> list, String user_id, String address, String phone, String total, String payment) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Convert to SQL timestamp
        Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);
        String query = "insert into orders ( user_id, order_date, receiving_date, address, phone, total, status_payment, payment, notes, status) values (?,?,null,?,?,?,0,?,null,1)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setTimestamp(2, sqlTimestamp);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, total);
            ps.setString(6, payment);
            ps.executeUpdate();
            String query1 = "select order_id from orders where user_id=? order by order_id desc limit 1";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, user_id);
            rs = ps1.executeQuery();
            while (rs.next()) {
                String order_id = rs.getString(1);
                for (Cart cart : list) {
                    String price = String.valueOf(Float.parseFloat(cart.getPrice()) * (100 - Float.parseFloat(cart.getSale_price())) / 100);
                    String query2 = "insert into orderdetail values (?,?,?,?,0)";
                    PreparedStatement ps2 = con.prepareStatement(query2);
                    ps2.setString(1, order_id);
                    ps2.setString(2, cart.getProduct_size_id());
                    ps2.setString(3, price);
                    ps2.setString(4, cart.getQuantity());
                    ps2.executeUpdate();
                    String query3 = "update product_size set quantity= quantity - ? where product_size_id=?";
                    PreparedStatement ps3 = con.prepareStatement(query3);
                    ps3.setString(1, cart.getQuantity());
                    ps3.setString(2, cart.getProduct_size_id());
                    ps3.executeUpdate();
                }
            }
        } catch (Exception e) {

        }
    }

    public ProductSize checkQuantityCheckOut(String psd) {
        String query = "select * from product_size where product_size_id =?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, psd);

            rs = ps.executeQuery();
            while (rs.next()) {
                ProductSize ps = new ProductSize();
                ps.setProductSizeId(rs.getString(1));
                ps.setSizeId(rs.getString(2));
                ps.setProductId(rs.getString(3));
                ps.setQuantity(rs.getString(4));
                ps.setStatus(rs.getString(5));

                return ps;
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void addOrderVNPay(String user_id, String address, String phone, String total, String payment) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Convert to SQL timestamp
        Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);
        String query = "insert into orders ( user_id, order_date, receiving_date, address, phone, total, status_payment, payment, notes, status) values (?,?,null,?,?,?,0,?,null,1)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setTimestamp(2, sqlTimestamp);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, total);
            ps.setString(6, payment);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
    
    public void addOrderVNPaySuccess(List<Cart> list, String user_id){
        String query1 = "select order_id from orders where user_id=? order by order_id desc limit 1";
        try {
            con = new DBContext().getConnection();
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, user_id);
            rs = ps1.executeQuery();
            while (rs.next()) {
                String order_id = rs.getString(1);
                for (Cart cart : list) {
                    String price = String.valueOf(Float.parseFloat(cart.getPrice()) * (100 - Float.parseFloat(cart.getSale_price())) / 100);
                    String query2 = "insert into orderdetail values (?,?,?,?,0)";
                    PreparedStatement ps2 = con.prepareStatement(query2);
                    ps2.setString(1, order_id);
                    ps2.setString(2, cart.getProduct_size_id());
                    ps2.setString(3, price);
                    ps2.setString(4, cart.getQuantity());
                    ps2.executeUpdate();
                    String query3 = "update product_size set quantity= quantity - ? where product_size_id=?";
                    PreparedStatement ps3 = con.prepareStatement(query3);
                    ps3.setString(1, cart.getQuantity());
                    ps3.setString(2, cart.getProduct_size_id());
                    ps3.executeUpdate();
                }
            }
            } catch (Exception e) {

        }
    }

    public List<Order> getListOrderByStatus(String status_id, String user_id) {
        List<Order> list = new ArrayList<>();
        String query = "select * from orders where status=? and user_id=? order by order_id desc";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, status_id);
            ps.setString(2, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getString(1));
                order.setUser_id(rs.getString(2));
                order.setOrder_date(rs.getString(3));
                order.setReceiving_date(rs.getString(4));
                order.setAddress(rs.getString(5));
                order.setPhone(rs.getString(6));
                order.setOrder_total(rs.getString(7));
                order.setFeedback_status(rs.getString(8));
                order.setPayment(rs.getString(9));
                order.setOrder_notes(rs.getString(10));
                order.setOrder_status(rs.getString(11));
                String query1 = "select od.order_id,od.price,od.quantity,p.product_name,p.img,s.size from orderdetail od left join product_size ps on od.product_size_id=ps.product_size_id left join product p on p.product_id=ps.product_id left join size s on s.size_id=ps.size_id where od.order_id=?";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, rs.getString(1));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    order.setPrice(rs1.getString(2));
                    order.setQuantity(rs1.getString(3));
                    order.setProduct_name(rs1.getString(4));
                    order.setImg(rs1.getString(5));
                    order.setSize_id(rs1.getString(6));
                }
                String query2 = "select COUNT(order_id) from orderdetail where order_id=?";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, rs.getString(1));
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    order.setQuantityoforder(rs2.getString(1));
                }
                list.add(order);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<OrderDetail> getInfOrderList() {
        List<OrderDetail> list = new ArrayList<>();
        String query = "select od.order_id,od.price,od.quantity,p.product_name,p.img,s.size from orderdetail od left join product_size ps on od.product_size_id=ps.product_size_id left join product p on p.product_id=ps.product_id left join size s on s.size_id=ps.size_id";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail order = new OrderDetail();
                order.setOrder_id(rs.getString(1));
                order.setPrice(rs.getString(2));
                order.setQuantity(rs.getString(3));
                order.setProduct_name(rs.getString(4));
                order.setImg(rs.getString(5));
                order.setSize(rs.getString(6));

                list.add(order);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Order getOrderNow(String user_id) {
        String query = "select * from orders where user_id=? order by order_id desc limit 1";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);

            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getString(1));
                order.setUser_id(rs.getString(2));
                order.setOrder_date(rs.getString(3));
                order.setAddress(rs.getString(5));
                order.setPhone(rs.getString(6));
                order.setOrder_total(rs.getString(7));
                order.setFeedback_status(rs.getString(8));
                order.setPayment(rs.getString(9));
                order.setOrder_status(rs.getString(11));

                return order;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void newCart(String user_id) {
        String query = "delete from cartdetail where user_id=?;";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user_id);

            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void updateOrderStatus(String order_id, String status) {
        String query = "update orders set status = ? where order_id=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, order_id);

            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void updateCart(String user_id, String psid, String quantity) {
        String query = "update  cartdetail set quantity= ? where user_id=? and "
                + "product_size_id=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, quantity);
            ps.setString(2, user_id);
            ps.setString(3, psid);

            ps.executeUpdate();

        } catch (Exception e) {

        }
    }
}
