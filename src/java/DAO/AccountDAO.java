/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.RoleFeature;
import Model.Roles;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PasswordHashing;

/**
 *
 * @author Luu Bach
 */
public class AccountDAO extends DBContext {

    public void changeAccountPass(String userGmail, String newPass) {
        String updateUsersSql = "UPDATE Users SET password = ? WHERE email = ?;";
        MD5 md5 = new MD5();
        newPass = md5.getMd5(newPass);
        Connection c = null;
        try {
            c = getConnection();
            // Set new password for Users
            try ( PreparedStatement updateStmtUsers = c.prepareStatement(updateUsersSql)) {
                updateStmtUsers.setString(1, newPass);
                updateStmtUsers.setString(2, userGmail);
                updateStmtUsers.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void UpdateUserInfoByAdmin(String email, String status) {
        try {
            String sql = "UPDATE `onlineshop`.`users`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `email` = ?;";
            Connection con = getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);

            ptm.setString(1, status);
            ptm.setString(2, email);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UpdateUserInfoByAdmin: " + ex.getMessage());
        }
    }

    public String getAccountName(String gmail) {
        String sql = "SELECT \n"
                + "      admin_name\n"
                + "      ,email\n"
                + "  FROM Admin \n"
                + "  WHERE email ='?'\n"
                + "  UNION ALL\n"
                + "SELECT user_name, email FROM Users\n"
                + "WHERE email ='?'";
        String accountName = "unknown";
        try {
            Connection connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gmail);
            stm.setString(2, gmail);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                accountName = rs.getString("accountName");
            }
        } catch (Exception e) {
        }
        return accountName;
    }

    public void changeEncodedAccount(String userGmail, String code) {
        String sql = "UPDATE Users\n"
                + "   SET \n"
                + "      user_code = ? \n"
                + " WHERE email=?";
        Connection c = null;
        try {
            c = getConnection();
            // Set new password for Users
            try ( PreparedStatement updateStmtUsers = c.prepareStatement(sql)) {
                updateStmtUsers.setString(1, code);
                updateStmtUsers.setString(2, userGmail);
                updateStmtUsers.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Users> getAllUsersExAdmin() {
        try {
            List<Users> listU = new ArrayList<>();
            String sql = "SELECT user_id\n"
                    + "      ,user_name\n"
                    + "      ,email\n"
                    + "      ,password\n"
                    + "      ,address\n"
                    + "      ,gender\n"
                    + "      ,phone\n"
                    + "      ,role_id\n"
                    + "      ,image\n"
                    + "      ,user_code\n"
                    + "      ,status\n"
                    + "  FROM Users where role_id != ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "5");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUser_id(rs.getString(1));
                u.setUser_name(rs.getString(2));
                u.setUser_email(rs.getString(3));
                u.setUser_pass(rs.getString(4));
                u.setUser_address(rs.getString(5));
                u.setUser_gender(rs.getString(6));
                u.setUser_phone(rs.getString(7));
                u.setUser_roleId(rs.getString(8));
                u.setUser_image(rs.getString(9));
                u.setUser_code(rs.getString(10));
                u.setUser_status(rs.getString(11));
                listU.add(u);
            }
            return listU;
        } catch (Exception ex) {
            System.out.println("getAllUsers: " + ex.getMessage());
        }
        return null;
    }

    public List<Roles> getAllRole() {
        try {
            List<Roles> listR = new ArrayList<>();
            String sql = "SELECT role_id\n"
                    + "      ,role_name\n"
                    + "  FROM Roles";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Roles r = new Roles();
                r.setRole_id(rs.getString(1));
                r.setRole_name(rs.getString(2));
                listR.add(r);
            }
            return listR;
        } catch (Exception ex) {
            System.out.println("listRole: " + ex.getMessage());
        }
        return null;

    }

    public List<Users> getUsersByRole(int i) {
        try {
            List<Users> list = new ArrayList<>();
            String sql = "select * from Users where role_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setInt(1, i);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString(1);
                String user_name = rs.getString(2);
                String user_email = rs.getString(3);
                String user_pass = rs.getString(4);
                String user_add = rs.getString(5);
                String user_gender = rs.getString(6);
                String user_phone = rs.getString(7);
                String user_roleId = rs.getString(8);
                String user_image = rs.getString(9);
                String user_code = rs.getString(10);
                String user_status = rs.getString(11);
                Users u = new Users(user_id, user_name, user_email, user_pass, user_add, user_gender, user_phone, user_roleId, user_image, user_code, user_status);
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getUsersByRole: " + e.getMessage());
        }
        return null;
    }

    public List<Users> getCusByCon(String s_name, String s_email, String s_phone, String s_status, String s_gender) {
        try {
            List<Users> list = new ArrayList<>();
            String sql = "select * from Users where user_name like ? and email like ?  and phone like ? and status like ?  and gender like ? and role_id = 4";

            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + s_name + "%");
            ptm.setString(2, "%" + s_email + "%");
            ptm.setString(3, "%" + s_phone + "%");
            ptm.setString(4, "%" + s_status + "%");
            ptm.setString(5, "%" + s_gender + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString(1);
                String user_name = rs.getString(2);
                String user_email = rs.getString(3);
                String user_pass = rs.getString(4);
                String user_add = rs.getString(5);
                String user_gender = rs.getString(6);
                String user_phone = rs.getString(7);
                String user_roleId = rs.getString(8);
                String user_image = rs.getString(9);
                String user_code = rs.getString(10);
                String user_status = rs.getString(11);
                Users u = new Users(user_id, user_name, user_email, user_pass, user_add, user_gender, user_phone, user_roleId, user_image, user_code, user_status);
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getCusByCon: " + e.getMessage());
        }
        return null;
    }

    public int getTotalCusByCon(String s_name, String s_email, String s_phone, String s_status, String s_gender) {
        try {
            List<Users> list = new ArrayList<>();
            String sql = "select count(*) from Users where user_name like ? and email like ?  and phone like ? and status like ?  and gender like ? and role_id = 4 ";

            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + s_name + "%");
            ptm.setString(2, "%" + s_email + "%");
            ptm.setString(3, "%" + s_phone + "%");
            ptm.setString(4, "%" + s_status + "%");
            ptm.setString(5, "%" + s_gender + "%");

            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                int totalProduct = rs.getInt(1);
                return totalProduct;
            }

        } catch (Exception e) {
            System.out.println("getCusByCon: " + e.getMessage());
        }
        return 0;
    }

    public int getTotalUsersByFilter(String gender, String role, String status, String name, String email, String mobile) {
        try {
            List<Users> list = new ArrayList<>();
            String sql = "select count(*) FROM Users where"
                    + " gender like ? and role_id like ? and status like ? and user_name like ? and email like ? and phone like ?";

            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + gender + "%");
            ptm.setString(2, "%" + role + "%");
            ptm.setString(3, "%" + status + "%");
            ptm.setString(4, "%" + name + "%");
            ptm.setString(5, "%" + email + "%");
            ptm.setString(6, "%" + mobile + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                int totalProduct = rs.getInt(1);
                return totalProduct;
            }

        } catch (Exception e) {
            System.out.println("getTotalUsersByFilter: " + e.getMessage());
        }
        return 0;
    }

    public void DeleteUserByAdmin(String email) {
        try {
            String sql = "DELETE FROM `onlineshop`.`users`\n"
                    + "WHERE email = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, email);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DeleteUserByAdmin: " + ex.getMessage());
        }
    }

    public List<Users> getUsersByAdmin(String gender, String role, String status, String name, String email, String mobile) {
        try {
            List<Users> list = new ArrayList<>();
            String sql = "select * from Users where gender like ? and role_id like ? and status like ? and user_name like ? and email like ? and phone like ? and role_id !=1 ";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, "%" + gender + "%");
            ptm.setString(2, "%" + role + "%");
            ptm.setString(3, "%" + status + "%");
            ptm.setString(4, "%" + name + "%");
            ptm.setString(5, "%" + email + "%");
            ptm.setString(6, "%" + mobile + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString(1);
                String user_name = rs.getString(2);
                String user_email = rs.getString(3);
                String user_pass = rs.getString(4);
                String user_add = rs.getString(5);
                String user_gender = rs.getString(6);
                String user_phone = rs.getString(7);
                String user_roleId = rs.getString(8);
                String user_image = rs.getString(9);
                String user_code = rs.getString(10);
                String user_status = rs.getString(11);
                Users u = new Users(user_id, user_name, user_email, user_pass, user_add, user_gender, user_phone, user_roleId, user_image, user_code, user_status);
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getUserByAdmin: " + e.getMessage());
        }
        return null;
    }

    public void changePassWithUserCode(String userCode, String newPass) throws Exception {
        String updateUsersSql = "UPDATE Users SET password = ? WHERE user_code = ?;";
        MD5 md5 = new MD5();
        newPass = md5.getMd5(newPass);
        Connection c = null;
        c = getConnection();
        // Set new password for Users
        try ( PreparedStatement updateStmtUsers = c.prepareStatement(updateUsersSql)) {
            updateStmtUsers.setString(1, newPass);
            updateStmtUsers.setString(2, userCode);
            updateStmtUsers.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignUpInDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Users getUserByID(String uid) {
        try {
            String sql = "SELECT user_id\n"
                    + "      ,user_name\n"
                    + "      ,email\n"
                    + "  FROM Users where user_id = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, uid);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUser_id(rs.getString(1));
                u.setUser_name(rs.getString(2));
                u.setUser_email(rs.getString(3));
                return u;
            }
        } catch (SQLException ex) {
            System.out.println("getUserByID: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<RoleFeature> getAllRoleFeature(Users user) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT u.email,u.role_id , rf.roleURL  FROM users u "
                + "INNER JOIN RoleFeature rf ON rf.role_id = u.role_id "
                + "WHERE u.user_id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(user.getUser_id()));
        ResultSet rs = stm.executeQuery();
        ArrayList<RoleFeature> roleFeatures = new ArrayList<>();
        while (rs.next()) {
            RoleFeature roleFeature = new RoleFeature();
            roleFeature.setRole_id(rs.getString("role_id"));
            roleFeature.setRoleURL(rs.getString("roleURL"));
            roleFeatures.add(roleFeature);
        }
        return roleFeatures;
    }

    public ArrayList<RoleFeature> getAllRoleFeatureForGuest(Users user) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM onlineshop.rolefeature WHERE role_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(user.getUser_roleId()));
        ResultSet rs = stm.executeQuery();
        ArrayList<RoleFeature> roleFeatures = new ArrayList<>();
        while (rs.next()) {
            RoleFeature roleFeature = new RoleFeature();
            roleFeature.setRole_id(rs.getString("role_id"));
            roleFeature.setRoleURL(rs.getString("roleURL"));
            roleFeatures.add(roleFeature);
        }
        return roleFeatures;
    }

    public Users getUserByEmail(String user_email) {
        try {
            String sql = "SELECT user_id\n"
                    + "      ,user_name\n"
                    + "      ,email\n"
                    + "  FROM Users where email = ?";
            Connection c = getConnection();
            PreparedStatement ptm = c.prepareStatement(sql);
            ptm.setString(1, user_email);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUser_id(rs.getString(1));
                u.setUser_name(rs.getString(2));
                u.setUser_email(rs.getString(3));
                return u;
            }
        } catch (SQLException ex) {
            System.out.println("getUserByID: " + ex.getMessage());
        }
        return null;
    }
}
