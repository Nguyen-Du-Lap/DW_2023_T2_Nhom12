package org.example;

import org.apache.poi.xdgf.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetDataWeb {
    public static List<ArrayList<String>> getData(String sourcePath, Connection connection) {
        List<ArrayList<String>> data = new ArrayList<>();

        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        String url = sourcePath;
        // Thiết lập web chạy ẩn
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        WebDriver driver = new EdgeDriver(options);
        driver.get(url);
        LocalDate currentDate = LocalDate.now();

        // Định dạng theo yêu cầu (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        // 12. Kiểm tra xem có lỗi không?
        try {
            List<WebElement> tableElements = driver.findElements(By.className("jrPage"));
            System.out.println(tableElements.size());
            WebElement secondTable = tableElements.get(Constant.TWO);

            List<WebElement> rows = secondTable.findElements(By.xpath(".//tr[@style='height:30px']"));

            for (WebElement row : rows) {
                List<WebElement> columns = row.findElements(By.xpath(".//td[contains(@style,'padding: 2px')]"));
                ArrayList<String> rowsText = new ArrayList<String>();
                for (WebElement column : columns) {
                    rowsText.add(column.getText());
                }
                rowsText.add(formattedDate);
                data.add(rowsText);
            }
            data.remove(Constant.ZERO);

        }catch (Exception e) {
            // 16. update status = FE
            ConfigDAO.updateStatus(connection, Constant.ID_CONFIG_ONE, String.valueOf(Status.FE));

            // 17. Ghi log extract thất bại
            LogDAO.insertLog(Constant.ID_CONFIG_ONE, "Extract thất bại ", Constant.ZERO, "Failed - Extract", "source tỷ giá", "fileExcel", "Lỗi đọc dữ liệu ở web, hoặc phiên bản webdriver không đúng.", connection);

            System.out.println("Khong tim thay du lieu");
        }

        driver.close();
        return data;
    }

}
