package org.example;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 2.Kết nối database control
            connection = MySQLConnectionManager.getConnection();

            // 3. Kiểm tra kết nối có thành công hay không?
            if (connection != null) {

                // 4. Ghi log bắt đầu extract
                LogDAO.insertLog(Constant.ID_CONFIG_ONE, "Bắt đầu extract", Constant.ZERO, "In Progress - Extracting", "source tỷ giá", "fileExcel", "Bắt đầu tiến hành extract dữ liệu.", connection);

                // 5. Load dữ liệu trong table config từ database control
                // 6. Dòng dữ liệu flag=1 và status= Default
                Configuration config = ConfigDAO.getConfigByFlagAndStatus(connection, Constant.FLAG_ONE, Constant.STATUS_DEFAULT);

                // 7. Kiểm xem tồn tại hay không?
                if (config != null) {

                    // 10. Update status=PE
                    ConfigDAO.updateStatus(connection, Constant.ID_CONFIG_ONE, String.valueOf(Status.PE));

                    // 11. Dùng source_path và format tiến hành extract
                    List<ArrayList<String>> data = GetDataWeb.getData(config.getSourcePath(), connection);

                    // 13. Ghi log đang extract
                    LogDAO.insertLog(Constant.ID_CONFIG_ONE, "Đang extract", Constant.ZERO, "In Progress - Extracting", "source tỷ giá", "fileExcel", "Đang tiến hành extract dữ liệu.", connection);

                    //14. Ghi dữ liệu vào file excel với tên là biến location
                    Excel.save(data, config.getLocation() + "." + config.getFormat(), config.getSeparator(), connection);
                } else {
                    // 8. Ghi log extract thất bại, flag=0 hoặc status != Default
                    LogDAO.insertLog(Constant.ID_CONFIG_ONE, "Extract thất bại, flag=0 hoặc status != Default", Constant.ZERO, "Failed - Extract", "source tỷ giá", "fileExcel", "Extract thất bại.", connection);

                    // 20. update status = FE
                    ConfigDAO.updateStatus(connection, Constant.ID_CONFIG_ONE, String.valueOf(Status.FE));
                }

            } else {
                // Đóng kết nối
                System.out.println("Không thể kết nối đến database_control!");
            }
        } catch (Exception e) {
            // 21. Ghi log ra file lưu ở D://datawarehouse/log/LogErroryyyy-MM-dd HH-mm-ss.txt
            LogLocal.create(e.getMessage());

        } finally {
            // Đóng kết nối sau khi sử dụng
            MySQLConnectionManager.closeConnection(connection);
        }
    }
}