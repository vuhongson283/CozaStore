/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.MD5;

import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePassDAO extends DBContext {

    public Users changePassUser(String u_email, String u_pass) {
        String sql = "update Users set password = ? where email = ? ";
                
        Connection c;
        try {
            MD5 md = new MD5();
            c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, md.getMd5(u_pass));
            ptm.setString(2, u_email);
            ptm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ChangePassDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }

}
