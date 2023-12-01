package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ConfigDAO {

    public static Config getConfigByFlagAndStatus(Connection connection) {
        Config config = null;
        String sql = "SELECT source_path, location, format, date_run FROM config WHERE flag = 1 AND status = 'default'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    config = new Config();
                    config.setSourcePath(resultSet.getString("source_path"));
                    config.setLocation(resultSet.getString("location"));
                    config.setFormat(resultSet.getString("format"));
                    config.setDateRun(resultSet.getDate("date_run"));
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