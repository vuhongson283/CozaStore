/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author PCMSI
 */
public class Cart {
      String user_id, product_size_id, size_id, product_id, product_name, price, category_id, supplier_id, img, status, sale_price, quantity, stockofquantity;

    public Cart() {
    }

    public Cart(String user_id, String product_size_id, String size_id, String product_id, String product_name, String price, String category_id, String supplier_id, String img, String status, String sale_price, String quantity, String stockofquantity) {
        this.user_id = user_id;
        this.product_size_id = product_size_id;
        this.size_id = size_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.img = img;
        this.status = status;
        this.sale_price = sale_price;
        this.quantity = quantity;
        this.stockofquantity = stockofquantity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_size_id() {
        return product_size_id;
    }

    public void setProduct_size_id(String product_size_id) {
        this.product_size_id = product_size_id;
    }

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStockofquantity() {
        return stockofquantity;
    }

    public void setStockofquantity(String stockofquantity) {
        this.stockofquantity = stockofquantity;
    }
    
}
