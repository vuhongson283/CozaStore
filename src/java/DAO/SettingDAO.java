/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class SettingDAO extends DBContext {

    public ArrayList<Setting> getAllSettingFilter(String value, String status, String type) throws SQLException {
        String sql = "SELECT s.setting_id,s.setting_value,CONCAT(IFNULL(CAST(c.category_id AS CHAR), ''), IFNULL(CAST(cb.categoryBlog_id AS CHAR), ''),IFNULL(CAST(su.supplier_id AS CHAR),'')) AS setting_order,sc.setting_category_name,CONCAT(IFNULL(CAST(c.status AS CHAR), ''), IFNULL(CAST(cb.status AS CHAR), ''),IFNULL(CAST(su.status AS CHAR),'')) AS status "
                + "FROM setting s "
                + "LEFT JOIN category c ON c.category_name = s.setting_value "
                + "LEFT JOIN categoryblog cb ON cb.categoryBlog_name = s.setting_value "
                + "LEFT JOIN supplier su ON su.supplier_name = s.setting_value "
                + "LEFT JOIN setting_category sc ON sc.setting_category_id = s.setting_category_id "
                + "WHERE s.setting_value LIKE ? AND CONCAT(IFNULL(CAST(c.status AS CHAR), ''), IFNULL(CAST(cb.status AS CHAR), ''), IFNULL(CAST(su.status AS CHAR),'')) LIKE ?  AND sc.setting_category_name LIKE ? ORDER BY s.setting_id;";
        Connection connection = getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + value + "%");
        stm.setString(2, "%" + status + "%");
        stm.setString(3, "%" + type + "%");
        ResultSet rs = stm.executeQuery();
        ArrayList<Setting> settings = new ArrayList<>();
        while (rs.next()) {
            Setting setting = new Setting();
            setting.setSettingId(rs.getInt("setting_id"));
            setting.setSettingOrder(rs.getInt("setting_order"));
            setting.setSettingValue(rs.getString("setting_value"));
            setting.setSettingCategory(rs.getString("setting_category_name"));
            setting.setStatus(rs.getBoolean("status"));
            settings.add(setting);
        }
        return settings;
    }

    public Setting getSetting(String settingId) throws SQLException {
        String sql = "SELECT s.setting_id,s.setting_value,CONCAT(IFNULL(CAST(c.category_id AS CHAR), ''), IFNULL(CAST(cb.categoryBlog_id AS CHAR), ''),IFNULL(CAST(su.supplier_id AS CHAR),'')) AS setting_order,sc.setting_category_name,CONCAT(IFNULL(CAST(c.status AS CHAR), ''), IFNULL(CAST(cb.status AS CHAR), ''),IFNULL(CAST(su.status AS CHAR),'')) AS status "
                + "FROM setting s "
                + "LEFT JOIN category c ON c.category_name = s.setting_value "
                + "LEFT JOIN categoryblog cb ON cb.categoryBlog_name = s.setting_value "
                + "LEFT JOIN supplier su ON su.supplier_name = s.setting_value "
                + "LEFT JOIN setting_category sc ON sc.setting_category_id = s.setting_category_id "
                + "WHERE s.setting_id = ?;";
        Connection connection = getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(settingId));
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Setting setting = new Setting();
            setting.setSettingId(rs.getInt("setting_id"));
            setting.setSettingOrder(rs.getInt("setting_order"));
            setting.setSettingValue(rs.getString("setting_value"));
            setting.setSettingCategory(rs.getString("setting_category_name"));
            setting.setStatus(rs.getBoolean("status"));
            return setting;
        }
        return null;
    }

    public void updateSetting(String settingId, String settingCategory, String settingOrder, String settingValue, String settingStatus) throws SQLException {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String updateValueSql = "UPDATE setting s SET s.setting_value = ? WHERE s.setting_id = ?";
            PreparedStatement stmUpdateValue = connection.prepareStatement(updateValueSql);
            stmUpdateValue.setString(1, settingValue);
            stmUpdateValue.setInt(2, Integer.parseInt(settingId));
            stmUpdateValue.executeUpdate();

            if (settingCategory.equals("Product Supplier")) {
                String updateStatusSql = "UPDATE Supplier SET status = ? ,supplier_name = ? WHERE  supplier_id = ?";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingStatus);
                stmUpdateStatus.setString(2, settingValue);
                stmUpdateStatus.setInt(3, Integer.parseInt(settingOrder));
                stmUpdateStatus.executeUpdate();
            } else if (settingCategory.equals("Product Category")) {
                String updateStatusSql = "UPDATE Category SET status = ? , category_name = ? WHERE  category_id = ?";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingStatus);
                stmUpdateStatus.setString(2, settingValue);
                stmUpdateStatus.setInt(3, Integer.parseInt(settingOrder));
                stmUpdateStatus.executeUpdate();
            } else if (settingCategory.equals("Blog Category")) {
                String updateStatusSql = "UPDATE categoryBlog SET status = ? , categoryBlog_name = ? WHERE  categoryBlog_id = ?";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingStatus);
                stmUpdateStatus.setString(2, settingValue);
                stmUpdateStatus.setInt(3, Integer.parseInt(settingOrder));
                stmUpdateStatus.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addNewSetting(String settingCategory, String settingValue, String settingStatus,String avatar) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String insertValueSql = "INSERT INTO Setting (setting_value, setting_category_id) VALUES (?,?)";
            PreparedStatement stmUpdateValue = connection.prepareStatement(insertValueSql);
            stmUpdateValue.setString(1, settingValue);
            stmUpdateValue.setInt(2, Integer.parseInt(settingCategory));
            stmUpdateValue.executeUpdate();

            if (settingCategory.equals("1")) {
                String updateStatusSql = "INSERT INTO Category(category_name,status) VALUES(?,?);";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingValue);
                stmUpdateStatus.setBoolean(2, Boolean.parseBoolean(settingStatus));
                stmUpdateStatus.executeUpdate();
            } else if (settingCategory.equals("2")) {
                String updateStatusSql = "INSERT INTO categoryBlog(categoryBlog_name,categoryBlog_img,status) VALUES(?,?,?);";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingValue);
                stmUpdateStatus.setString(2, avatar);
                stmUpdateStatus.setBoolean(3, Boolean.parseBoolean(settingStatus));
                stmUpdateStatus.executeUpdate();
            } else if (settingCategory.equals("3")) {
                String updateStatusSql = "INSERT INTO Supplier(supplier_name,status) VALUES(?,?);";
                PreparedStatement stmUpdateStatus = connection.prepareStatement(updateStatusSql);
                stmUpdateStatus.setString(1, settingValue);
                stmUpdateStatus.setBoolean(2, Boolean.parseBoolean(settingStatus));
                stmUpdateStatus.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
