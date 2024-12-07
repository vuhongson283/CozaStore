/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Size;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class SizeDAO extends DBContext {
        public List<Size> getAllSizes(){
        try {
            List<Size> list = new ArrayList<>();
            String sql ="SELECT * FROM onlineshop.size;";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {                
                Size s = new Size();
                s.setSize_id(rs.getString(1));
                s.setSize(rs.getString(2));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    public ArrayList<Size> getAllSize() throws SQLException {
        ArrayList<Size> sizes = new ArrayList<>();
        String sql = "SELECT size FROM onlineshop.size;";
        Connection c = getConnection();
        PreparedStatement ptm = c.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        while (rs.next()) {
            Size size = new Size();
            size.setSize(String.valueOf(rs.getInt("size")));
            sizes.add(size);
        }
        return sizes;
    }

}
