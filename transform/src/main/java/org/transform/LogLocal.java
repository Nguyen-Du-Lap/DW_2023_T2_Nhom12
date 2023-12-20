package org.transform;

import org.transform.connects.ConfigProperties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogLocal {
    public static void create(String message) {
        // Lấy ra ngày giờ hiện tại
        Date currentDateTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = formatter.format(currentDateTime);
        Path path = Paths.get(ConfigProperties.getSaveLog(), "LogError" + formattedDateTime+ ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            // Ghi dữ liệu vào file
            writer.write( "Module : Tranform /n Error Connect Database Control : " + message);
            System.out.println("Dữ liệu đã được ghi vào file '" + "LogError" + formattedDateTime+ ".txt" + "' thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}