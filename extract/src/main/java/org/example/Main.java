package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Biến đại diện cho kết nối
        Connection connection = null;

        try {
            // Kết nối đến MySQL
            connection = DriverManager.getConnection(ConfigProperties.getJdbcURL(), ConfigProperties.getUsername(), ConfigProperties.getPassword());

            // Kiểm tra kết nối thành công
            if (connection != null) {
                System.out.println("Kết nối thành công đến database_control!");
            } else {
                System.out.println("Không thể kết nối đến database_control!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối sau khi sử dụng
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}