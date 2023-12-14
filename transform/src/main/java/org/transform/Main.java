package org.transform;

import org.transform.connects.ConfigProperties;
import org.transform.connects.MySQLConnectionManager;
import org.transform.daos.ConfigDAO;
import org.transform.daos.ExchangeRateDAO;
import org.transform.daos.ExchangeRateFactDAO;
import org.transform.daos.LogDAO;
import org.transform.models.Configuration;
import org.transform.models.Status;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connectionControl = null;
        Connection connectionStaging = null;
        Connection connectionWarehouse = null;
        try {
            // 1. Load file config init  có tên config.properties
            // 2. Kết nối database control
            connectionControl = MySQLConnectionManager.getConnection(ConfigProperties.getJdbcURL(), ConfigProperties.getUsername(), ConfigProperties.getPassword());

            // 3. Kiểm tra kết nối có thành công hay không?
            if (connectionControl != null) {
                //  4. Load dòng trường dữ liệu có status=CL và flag= 1 trong table config từ database control
                Configuration config = ConfigDAO.getConfigByFlagAndStatus(connectionControl);

                // 5. Kiểm tra dữ liệu có tồn tại hay không
                if (config != null) {
                    // 6. Cập nhật status = PT
                    ConfigDAO.updateStatus(connectionControl, Constant.ID_CONFIG, String.valueOf(Status.PT));

                   // 7. Kết nối Database_Warehouse và Database_Staging
                    String urlStaging = MySQLConnectionManager.urlFormat(config.getServerName(),config.getPort(),config.getDatabaseNameStaging());
                    String urlWarehouse = MySQLConnectionManager.urlFormat(config.getServerName(),config.getPort(),config.getDatabaseNameWarehouse());
                    connectionStaging = MySQLConnectionManager.getConnection(urlStaging, config.getUsername(), config.getPassword());
                    connectionWarehouse = MySQLConnectionManager.getConnection(urlWarehouse, config.getUsername(), config.getPassword());
                    // 8. Kiểm tra kết nối có thành công hay không?
                    if (connectionStaging != null && connectionWarehouse != null) {
                        // 11. Ghi log tiến hành transform
                        LogDAO.insertLog(Constant.ID_CONFIG, "Tiến hành transform", 0, "In Progress - Transform", "data_staging", "data_warehouse", "Transform thất bại, kết nối đến database staging và warehouse không thành công", connectionControl);

                        // 12. Dựa vào dữ liệu config để load dữ liệu từ Database_Staging vào table exchange_rate_fact trong Database_Warehouse
                        int rows = ExchangeRateFactDAO.insert(connectionWarehouse, config.getDateRun());

                        // 13. Kiểm tra có thành công hay không?
                        if(rows > Constant.ZERO) {
                            // 16. Cập nhật status = CT
                            ConfigDAO.updateStatus(connectionControl, Constant.ID_CONFIG, String.valueOf(Status.CT));

                            // 17. Truncate bảng exchange_rate trong  db staging
                            ExchangeRateDAO.truncate(connectionStaging);

                            // 18. Ghi log transform thành công
                            LogDAO.insertLog(Constant.ID_CONFIG, "Transform thành công", rows, "Completed - Transform", "data_staging", "data_warehouse", "Transform thành công", connectionControl);
                        }else {
                            // 14. Cập nhật status = FT
                            ConfigDAO.updateStatus(connectionControl, Constant.ID_CONFIG, String.valueOf(Status.FT));

                            // 15. Ghi log vào table log trong db control
                            LogDAO.insertLog(Constant.ID_CONFIG, "Transform thất bại", rows, "Failed - Transform", "data_staging", "data_warehouse", "Load dữ liệu từ Database_Staging vào table exchange_rate_fact thất bại", connectionControl);
                        }
                    }else {
                        // 9. Ghi log vào table log trong database control
                        LogDAO.insertLog(Constant.ID_CONFIG, "transform", Constant.ZERO, "Failed - Transform", "data_staging", "data_warehouse", "Transform thất bại, kết nối đến database staging và warehouse không thành công", connectionControl);

                        // 10. Cập nhật status = FT
                        ConfigDAO.updateStatus(connectionControl, Constant.ID_CONFIG, String.valueOf(Status.FT));
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