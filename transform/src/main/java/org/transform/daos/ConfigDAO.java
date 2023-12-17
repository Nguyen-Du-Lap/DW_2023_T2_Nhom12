package org.transform.daos;

import org.transform.models.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ConfigDAO {

    // 5. Kiểm tra status=CL và flag= 1 không
    public static Configuration getConfigByFlagAndStatus(Connection connection) {
        Configuration config = null;
//        String sql = "SELECT database_name_staging, database_name_warehouse, server_name, port, username, password" +
//                "FROM config WHERE flag = 1 AND status = 'CL'";
        String sql = "SELECT database_name_staging, database_name_warehouse, server_name, port, username, password\n" +
                "FROM config WHERE flag = 1 AND status = 'CL'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    config = new Configuration();
                    config.setDatabaseNameStaging(resultSet.getString(1));
                    config.setDatabaseNameWarehouse(resultSet.getString(2));
                    config.setServerName(resultSet.getString(3));
                    config.setPort(resultSet.getString(4));
                    config.setUsername(resultSet.getString(5));
                    config.setPassword(resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }

    public static void updateStatus(Connection connection, int configId, String status) {
        String sql = "UPDATE config SET status = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, configId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật status thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi cần cập nhật.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}