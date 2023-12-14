package org.transform;

import org.transform.connects.ConfigProperties;
import org.transform.connects.MySQLConnectionManager;
import org.transform.daos.ConfigDAO;
import org.transform.daos.ExchangeRateFactDAO;
import org.transform.models.Configuration;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connectionControl = null;
        Connection connectionStaging = null;
        Connection connectionWarehouse = null;
        try {
            // Lấy kết nối từ MySQLConnectionManager
            connectionControl = MySQLConnectionManager.getConnection(ConfigProperties.getJdbcURL(), ConfigProperties.getUsername(), ConfigProperties.getPassword());

            // Kiểm tra kết nối thành công
            if (connectionControl != null) {
                System.out.println("Kết nối thành công đến database_control!");

                // Log Tiến hành transform
//                LogDAO.insertLog(1, "Bắt đầu transform", 0, "In Progress - Transform, "2023-01-01", "fileExcel", "Bắt đầu tiến hành transform dữ liệu.", connection);

                // Lấy dữ liệu từ configuration với điều kiện flag = 1 và status = 'default'
                Configuration config = ConfigDAO.getConfigByFlagAndStatus(connectionControl);

                // Tồn tại
                if (config != null) {
                    // update status=PT
//                ConfigDAO.updateStatusToPE(connection, 1, String.valueOf(Status.PT));
//
                   // Kết nối DB Staging và Warehouse
                    String urlStaging = MySQLConnectionManager.urlFormat(config.getServerName(),config.getPort(),config.getDatabaseNameStaging());
                    String urlWarehouse = MySQLConnectionManager.urlFormat(config.getServerName(),config.getPort(),config.getDatabaseNameWarehouse());
                    connectionStaging = MySQLConnectionManager.getConnection(urlStaging, config.getUsername(), config.getPassword());
                    connectionWarehouse = MySQLConnectionManager.getConnection(urlWarehouse, config.getUsername(), config.getPassword());
                    if (connectionStaging != null && connectionWarehouse != null) {
                        System.out.println("Kết nối thành công đến database_staging!");
                        System.out.println("Kết nối thành công đến database_warehouse!");
                        ExchangeRateFactDAO.insert(connectionWarehouse, config.getDateRun());
                    }else {
                        System.out.println("Không thể kết nối đến database_staging và database_warehouse!");
                    }
                }
            } else {
                System.out.println("Không thể kết nối đến database_control!");
            }
        } finally {
            // Đóng kết nối sau khi sử dụng
            MySQLConnectionManager.closeConnection(connectionStaging);
            MySQLConnectionManager.closeConnection(connectionWarehouse);
            MySQLConnectionManager.closeConnection(connectionControl);
        }


    }
}