/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.SettingCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luu Bach
 */
public class SettingCategoryDAO extends DBContext {

    public ArrayList<SettingCategory> getAllSettingCategory() throws SQLException {
        String sql = "SELECT * FROM onlineshop.setting_category; ";
        Connection connection = getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        ArrayList<SettingCategory> settingCategorys = new ArrayList<>();
        while (rs.next()) {
            SettingCategory settingCategory = new SettingCategory();
            settingCategory.setSettingCategoryId(rs.getInt("setting_category_id"));
            settingCategory.setSettingCatogoryName(rs.getString("setting_category_name"));
            settingCategorys.add(settingCategory);
        }
        return settingCategorys;
    }

}
