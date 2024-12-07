/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author TTT
 */
public class Users {

    String user_id, user_name, user_email, user_pass, user_address, user_gender, user_phone, user_roleId, user_image, user_code, user_status;
    ArrayList<RoleFeature> rolFeatures;

    public Users() {
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public ArrayList<RoleFeature> getRolFeatures() {
        return rolFeatures;
    }

    public void setRolFeatures(ArrayList<RoleFeature> rolFeatures) {
        this.rolFeatures = rolFeatures;
    }

    public Users(String user_id, String user_name, String user_email, String user_pass, String user_address, String user_gender, String user_phone, String user_roleId, String user_image, String user_code, String user_status) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_address = user_address;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_roleId = user_roleId;
        this.user_image = user_image;
        this.user_code = user_code;
        this.user_status = user_status;
    }

    public Users(String user_name, String user_email, String user_pass, String user_address, String user_gender, String user_phone, String user_roleId, String user_image, String user_code, String user_status) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_address = user_address;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_roleId = user_roleId;
        this.user_image = user_image;
        this.user_code = user_code;
        this.user_status = user_status;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
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

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_roleId() {
        return user_roleId;
    }

    public void setUser_roleId(String user_roleId) {
        this.user_roleId = user_roleId;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

}
