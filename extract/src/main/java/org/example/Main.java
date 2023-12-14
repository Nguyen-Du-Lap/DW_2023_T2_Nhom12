package org.example;

import java.sql.Connection;
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
                LogDAO.insertLog(1, "Bắt đầu extract", 0, "In Progress - Extracting", "source tỷ giá", "fileExcel", "Bắt đầu tiến hành extract dữ liệu.", connection);

                // 5. Load dữ liệu trong table config từ database control
                // 6. Dòng dữ liệu flag=1 và status= Default
                Configuration config = ConfigDAO.getConfigByFlagAndStatus(connection);

                // 7. Kiểm xem tồn tại hay không?
                if (config != null) {

                    // 10. Update status=PE
                    ConfigDAO.updateStatus(connection, 1, String.valueOf(Status.PE));

                    // 11. Dùng source_path và format tiến hành extract
                    List<ArrayList<String>> data = GetDataWeb.getData(config.getSourcePath(), connection);

                    // 13. Ghi log đang extract
                    LogDAO.insertLog(1, "Đang extract", 0, "In Progress - Extracting", "source tỷ giá", "fileExcel", "Đang tiến hành extract dữ liệu.", connection);

                    //14. Ghi dữ liệu vào file excel với tên là biến location
                    Excel.save(data, config.getLocation()+"."+config.getFormat(), config.getSeparator(), connection);
                }else {
                    // 8. Ghi log extract thất bại, flag=0 hoặc status != Default
                    LogDAO.insertLog(1, "Extract thất bại, flag=0 hoặc status != Default", 0, "Failed - Extract", "source tỷ giá", "fileExcel", "Extract thất bại.", connection);

                    // 20. update status = FE
                    ConfigDAO.updateStatus(connection, 1, String.valueOf(Status.FE));
                }

            } else {
                // Đóng kết nối
                System.out.println("Không thể kết nối đến database_control!");
            }
        } finally {
            // Đóng kết nối sau khi sử dụng
            MySQLConnectionManager.closeConnection(connection);
        }
    }
}