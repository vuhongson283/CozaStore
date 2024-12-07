package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/onlineshop";
        String user = "root";
        String password = "lam1512003";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
        return null;

    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

}
