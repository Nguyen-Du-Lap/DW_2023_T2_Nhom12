package org.example;

import org.apache.poi.xdgf.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetDataWeb {
    public List<ArrayList<String>> getData() {
        List<ArrayList<String>> data = new ArrayList<>();

        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        String url = ConfigProperties.getLink();
        // Thiết lập web chạy ẩn
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        WebDriver driver = new EdgeDriver(options);
        driver.get(url);
        LocalDate currentDate = LocalDate.now();

        // Định dạng theo yêu cầu (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        try {
            List<WebElement> tableElements = driver.findElements(By.className("jrPage"));
            System.out.println(tableElements.size());
            WebElement secondTable = tableElements.get(2);

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
            data.remove(0);

        }catch (Exception e) {
            System.out.println("Khong tim thay du lieu");
        }


        driver.close();
        return data;
    }

}
