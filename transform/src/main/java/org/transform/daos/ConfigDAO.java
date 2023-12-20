package org.transform.daos;

import org.transform.models.Configuration;

import java.sql.*;

public class ConfigDAO {

    // 5. Kiểm tra status=CL và flag= 1 không
    public static Configuration getConfigByFlagAndStatus(Connection connection, int flag, String status) {
        Configuration configuration = null;
        String sql = "{CALL load_configByFlagAndStatus(?, ?)}"; // Thay thế tên stored procedure và tham số tương ứng

        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setInt(1, flag); // Thiết lập giá trị cho tham số flag
            statement.setString(2, status); // Thiết lập giá trị cho tham số status

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    configuration = new Configuration();
                    configuration.setDatabaseNameStaging(resultSet.getString("database_name_staging"));
                    configuration.setDatabaseNameWarehouse(resultSet.getString("database_name_warehouse"));
                    configuration.setServerName(resultSet.getString("server_name"));
                    configuration.setPort(resultSet.getString("port"));
                    configuration.setUsername(resultSet.getString("username"));
                    configuration.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public static void updateStatus(Connection connection, int configId, String status) {
        String sql = "{CALL update_config(?, ?)}";
        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, configId);
            callableStatement.setString(2, status);

            int rowsAffected = callableStatement.executeUpdate();
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