/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PCMSI
 */
public class UpdateProfileDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void updateProfile(String name, String gender, String phone, String address, String email) {
        String query = "update Users set user_name=?,gender=?,phone=?,address=? where email=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProfileAvatar(String avatar, String email) {
        String query = "update Users set image=? where email=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, avatar);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
public void banAcc(String email){
     String query = "update Users set status=0 where email=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
}
public void unbanAcc(String email){
    String query = "update Users set status=1 where email=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
}

 public void setAvatarDefault( String email) {
        String query = "update Users set image='avatarauto.jpg' where email=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}