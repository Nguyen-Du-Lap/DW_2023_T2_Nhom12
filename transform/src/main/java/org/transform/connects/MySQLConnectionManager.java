package org.transform.connects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager {

    // Phương thức để lấy kết nối đến MySQL
    public static Connection getConnection(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể lấy kết nối đến database!");
        }
    }

    // Phương thức để đóng kết nối
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể đóng kết nối!");
            }
        }
    }
    public static String urlFormat(String serverName, String port, String databaseName) {
        return "jdbc:mysql://"+serverName+":"+port+"/"+databaseName;
    }
}