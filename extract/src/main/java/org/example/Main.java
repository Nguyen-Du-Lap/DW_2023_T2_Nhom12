package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Lấy kết nối từ MySQLConnectionManager
            connection = MySQLConnectionManager.getConnection();

            // Kiểm tra kết nối thành công
            if (connection != null) {
                System.out.println("Kết nối thành công đến database_control!");

                // Log Tiến hành extract
//                LogDAO.insertLog(1, "Bắt đầu extract", 0, "In Progress - Extracting", "2023-01-01", "fileExcel", "Bắt đầu tiến hành extract dữ liệu.", connection);

                // Lấy dữ liệu từ config với điều kiện flag = 1 và status = 'default'
                Config config = ConfigDAO.getConfigByFlagAndStatus(connection);

                String sourcePath = null;
                String location = null;
                String format = null;
                Date dateRun = null;
                // Tồn tại
                if (config != null) {
                    // Lưu các biến
                    sourcePath = config.getSourcePath();
                    location = config.getLocation();
                    format = config.getFormat();
                    dateRun = config.getDateRun();
                    System.out.println(sourcePath);
                }

//                // update status=PE
//                ConfigDAO.updateStatusToPE(connection, 1, String.valueOf(Status.PE));
//
                // Tiến hành extract
                List<ArrayList<String>> data = GetDataWeb.getData(sourcePath);

                //Ghi log đang extract
                
                //Ghi dữ liệu vào file execel
                Excel.save(data, location+"."+format);
            } else {
                System.out.println("Không thể kết nối đến database_control!");
            }
        } finally {
            // Đóng kết nối sau khi sử dụng
            MySQLConnectionManager.closeConnection(connection);
        }
    }
}