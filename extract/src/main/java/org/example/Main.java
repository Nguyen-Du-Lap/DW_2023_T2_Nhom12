package org.example;

import java.sql.Connection;
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
                LogDAO.insertLog(1, "Bắt đầu extract", 0, "In Progress - Extracting", "2023-01-01", "fileExcel", "Bắt đầu tiến hành extract dữ liệu.", connection);

                // Lấy dữ liệu từ configuration với điều kiện flag = 1 và status = 'default'
                Configuration config = ConfigDAO.getConfigByFlagAndStatus(connection);

                // Tồn tại
                if (config != null) {
//                // update status=PE
//                    ConfigDAO.updateStatus(connection, 1, String.valueOf(Status.PE));
//
                    // Tiến hành extract
                    List<ArrayList<String>> data = GetDataWeb.getData(config.getSourcePath());

                    //Ghi log đang extract
                    LogDAO.insertLog(1, "Đang extract", 0, "In Progress - Extracting", "2023-01-01", "fileExcel", "Đang tiến hành extract dữ liệu.", connection);
                    //Ghi dữ liệu vào file execel
                    Excel.save(data, config.getLocation()+"."+config.getFormat());
                }

            } else {
                System.out.println("Không thể kết nối đến database_control!");
            }
        } finally {
            // Đóng kết nối sau khi sử dụng
            MySQLConnectionManager.closeConnection(connection);
        }
    }
}