package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    public static void save(List<ArrayList<String>> data, String locationFormat, String separator) {
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

            System.out.println("Dữ liệu đã được lưu vào tệp tin thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void read() {

    }

}
