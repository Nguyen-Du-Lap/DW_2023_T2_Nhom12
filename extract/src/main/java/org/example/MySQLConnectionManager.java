package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager {

    // Phương thức để lấy kết nối đến MySQL
    public static Connection getConnection() {
        try {
            //1. Load file config init  có tên config.properties
            return DriverManager.getConnection(ConfigProperties.getJdbcURL(), ConfigProperties.getUsername(), ConfigProperties.getPassword());
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
}
