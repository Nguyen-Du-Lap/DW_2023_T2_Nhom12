package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    public void save() {
        // Tạo Workbook và Sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Currency Data");

        // Tạo Header Row
//        String currentCurrency = ConfigProperties.getCurrentCurrency();
//        String[] columns = {"Currency", "Price ("+currentCurrency+")"};
        Row headerRow = sheet.createRow(0);
//        for (int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//        }
//
//        // Thêm dữ liệu vào Sheet
//        for (int i = 0; i < data.size(); i++) {
//            Row row = sheet.createRow(i + 1);
//            String[] rowData = data.get(i);
//            for (int j = 0; j < rowData.length; j++) {
//                row.createCell(j).setCellValue(rowData[j]);
//            }
//        }
        // Đường dẫn đầy đủ cho tệp đầu ra
        String outputPath = "C:\\DataExcelCurrency\\path_to_file.xlsx";
        // Lưu Workbook vào tệp Excel
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Excel file has been generated successfully.");
    }

    public void read() {
        String excelFilePath = "C:\\DataExcelCurrency\\path_to_file.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            // Duyệt qua từng hàng trong sheet
            for (Row row : sheet) {
                Cell currencyCell = row.getCell(0); // Cột "Currency"
                Cell priceCell = row.getCell(1);    // Cột "Price (VND)"

                if (currencyCell != null && priceCell != null) {
                    String currency = currencyCell.getStringCellValue();
                    String price = priceCell.getStringCellValue();

                    System.out.println("Currency: " + currency + ", Price: " + price);
                }
            }

            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
