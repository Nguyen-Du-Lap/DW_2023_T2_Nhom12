package org.transform.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class LogDAO {

    public static void insertLog(int configId, String name, int rowCount, String status,
                                 String dataRangeFrom, String dataRangeTo, String message, Connection connection) {
        String sql = "{CALL insert_log(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setInt(1, configId);
            statement.setString(2, name);
            statement.setInt(3, rowCount);
            statement.setString(4, status);
            statement.setString(5, dataRangeFrom);
            statement.setString(6, dataRangeTo);
            statement.setString(7, message);
            statement.executeUpdate();

            System.out.println("Dữ liệu log đã được thêm vào bảng log.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Không thể thêm dữ liệu log vào bảng log.");
        }
    }
}