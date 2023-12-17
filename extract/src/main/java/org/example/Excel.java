package org.example;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    public static void save(List<ArrayList<String>> data, String locationFormat, String separator, Connection connection) {
        // 15. Kiểm tra xem lưu file thành công chưa
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(locationFormat)))) {
            for (ArrayList<String> row : data) {
                StringBuilder rowStringBuilder = new StringBuilder();

                // Ghi từng đối tượng trong hàng, cách nhau bằng dấu phẩy
                for (String item : row) {
                    rowStringBuilder.append(item).append(separator);
                }

                // Loại bỏ dấu phẩy cuối cùng và ghi vào tệp tin
                pr.println(rowStringBuilder.deleteCharAt(rowStringBuilder.length() - 1).toString());
            }
            // 18. update status = CE
            ConfigDAO.updateStatus(connection, 1, String.valueOf(Status.CE));

            // 19. Ghi log extract thành công
            LogDAO.insertLog(1, "Extract thành công", data.size(), "Completed - Extract", "source tỷ giá", "fileExcel", "Quá trinh lưu vào file thất bại!.", connection);

            System.out.println("Dữ liệu đã được lưu vào tệp tin thành công.");
        } catch (IOException e) {
            // 16. update status = FE
            ConfigDAO.updateStatus(connection, 1, String.valueOf(Status.FE));

            // 17. Ghi log extract thất bại
            LogDAO.insertLog(1, "Extract thất bại", 0, "Failed - Extract", "source tỷ giá", "fileExcel", "Quá trinh lưu vào file thất bại!.", connection);
            e.printStackTrace();
        }


    }

    public void read() {

    }

}
