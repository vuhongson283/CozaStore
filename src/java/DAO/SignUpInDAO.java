/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TTT
 */
public class SignUpInDAO extends DBContext {

    public Boolean checkUser(String u_email, String u_pass) {
        String sql = "select * from Users where email = ? and password = ?";
        Connection c;
        try {
            c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, u_email);
            ptm.setString(2, u_pass);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean checkAccountExisted(String u_email) {
        try {
            Connection c;
            c = getConnection();
            String sql2 = "select email from Users where email = ?";
            PreparedStatement ps2 = c.prepareStatement(sql2);
            ps2.setString(1, u_email);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void AddUsers(Users u) {
        MD5 md = new MD5();
        try {
            String sql = "INSERT INTO Users\n"
                    + "           (user_name\n"
                    + "           ,email\n"
                    + "           ,password\n"
                    + "           ,address\n"
                    + "           ,gender\n"
                    + "           ,phone\n"
                    + "           ,role_id\n"
                    + "           ,image\n"
                    + "           ,user_code,status)\n"
                    + "     VALUES(?,?,?,?,?,?,?,?,?,?)";
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, u.getUser_name());
            ps.setString(2, u.getUser_email());
            ps.setString(3, md.getMd5(u.getUser_pass()));
            ps.setString(4, u.getUser_address());
            ps.setString(5, u.getUser_gender());
            ps.setString(6, u.getUser_phone());
            ps.setInt(7, Integer.parseInt(u.getUser_roleId()));
            ps.setString(8, u.getUser_image());
            ps.setString(9, u.getUser_code());
            ps.setString(10, u.getUser_status());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("AddUsers: " + ex.getMessage());
        }
    }

      public void changeStatus(String user_email) {
        try {
            String sql = "UPDATE Users\n"
                    + "   SET status = ?\n"
                    + " WHERE email = ?";
            Connection c =getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "1");
            ptm.setString(2, user_email);
            ptm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger("changeStatus: " + ex.getMessage());
        }
    }

 

    public Users GetUser(String u_email) {
        try {
            Users u = new Users();
            String sql = "select * from Users where email = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, u_email);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString(1);
                String user_name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String address = rs.getString(5);
                String gender = rs.getString(6);
                String phone = rs.getString(7);
                String role_id = rs.getString(8);
                String image = rs.getString(9);
                String user_code = rs.getString(10);
                String user_status = rs.getString(11);
                u = new Users(user_id, user_name, email, password, address, gender, phone, role_id, image, user_code,user_status);
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }

   

}
