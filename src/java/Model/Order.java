/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TTT
 */
public class Order {
    String order_id,user_id, order_date,receiving_date, address, phone,  order_total,feedback_status,payment, order_notes, order_status,user_name,img,product_name,price,size_id,quantity,quantityoforder;

    public Order() {
    }

    public Order(String order_id, String user_id, String order_date, String receiving_date, String address, String phone, String order_total, String payment, String order_notes, String order_status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.receiving_date = receiving_date;
        this.address = address;
        this.phone = phone;
        this.order_total = order_total;
        this.payment = payment;
        this.order_notes = order_notes;
        this.order_status = order_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(String receiving_date) {
        this.receiving_date = receiving_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrder_total() {
        return order_total;
    }

    public void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrder_notes() {
        return order_notes;
    }

    public void setOrder_notes(String order_notes) {
        this.order_notes = order_notes;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getFeedback_status() {
        return feedback_status;
    }

    public void setFeedback_status(String feedback_status) {
        this.feedback_status = feedback_status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityoforder() {
        return quantityoforder;
    }

    public void setQuantityoforder(String quantityoforder) {
        this.quantityoforder = quantityoforder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    
    
}
