/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author TTT
 */
public class Product {

    String product_id;
    String product_name, img, des;
    String product_price;
    String product_saleprice;
    String category_id;
    String supplier_id;
    String status;
    String create_date;
    ArrayList<ProductSize> productSizes;

    public Product() {
    }

    public Product(String product_id, String product_name, String img, String des, String product_price, String product_saleprice, String category_id, String supplier_id, String status, String create_date, ArrayList<ProductSize> productSizes) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.img = img;
        this.des = des;
        this.product_price = product_price;
        this.product_saleprice = product_saleprice;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.status = status;
        this.create_date = create_date;
        this.productSizes = productSizes;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_saleprice() {
        return product_saleprice;
    }

    public void setProduct_saleprice(String product_saleprice) {
        this.product_saleprice = product_saleprice;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public ArrayList<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(ArrayList<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

}
