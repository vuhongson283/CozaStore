/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TTT
 */
public class Feedback {
    String feedback_id, feedback_des
            , feedback_rate, feedback_img
            , feedback_status, user_id
            , user_name, user_email, user_phone
            , product_id, product_name;

    public Feedback() {
    }

    public Feedback(String feedback_id, String feedback_des, String feedback_rate, String feedback_img, String feedback_status, String user_id, String user_name, String user_email, String user_phone, String product_id, String product_name) {
        this.feedback_id = feedback_id;
        this.feedback_des = feedback_des;
        this.feedback_rate = feedback_rate;
        this.feedback_img = feedback_img;
        this.feedback_status = feedback_status;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.product_id = product_id;
        this.product_name = product_name;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_des() {
        return feedback_des;
    }

    public void setFeedback_des(String feedback_des) {
        this.feedback_des = feedback_des;
    }

    public String getFeedback_rate() {
        return feedback_rate;
    }

    public void setFeedback_rate(String feedback_rate) {
        this.feedback_rate = feedback_rate;
    }

    public String getFeedback_img() {
        return feedback_img;
    }

    public void setFeedback_img(String feedback_img) {
        this.feedback_img = feedback_img;
    }

    public String getFeedback_status() {
        return feedback_status;
    }

    public void setFeedback_status(String feedback_status) {
        this.feedback_status = feedback_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
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

    

    

    
    
}
