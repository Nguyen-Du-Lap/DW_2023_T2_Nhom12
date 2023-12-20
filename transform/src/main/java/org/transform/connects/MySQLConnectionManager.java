package org.transform.connects;
import org.transform.Constant;
import org.transform.daos.ConfigDAO;
import org.transform.daos.LogDAO;
import org.transform.models.Status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager {


    public static Connection getConnection(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể lấy kết nối đến database!");
        }
    }

    public static Connection getConnection(String url, String username, String password, Connection connectionControl) {
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            // 9. Ghi log vào table log trong database control
            LogDAO.insertLog(Constant.ID_CONFIG, "Kết nối db staging và warehouse để transform", Constant.ZERO, "Failed - Transform", "data_staging", "data_warehouse", "Transform thất bại, kết nối đến database staging và warehouse không thành công", connectionControl);

            // 10. Cập nhật status = FT
            ConfigDAO.updateStatus(connectionControl, Constant.ID_CONFIG, String.valueOf(Status.FT));
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