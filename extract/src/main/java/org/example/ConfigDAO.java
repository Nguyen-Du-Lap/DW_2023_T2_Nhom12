package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ConfigDAO {

    public static Configuration getConfigByFlagAndStatus(Connection connection) {
        Configuration configuration = null;
        String sql = "SELECT * FROM config WHERE flag = 1 AND status = 'default'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    configuration = new Configuration();
                    configuration.setSourcePath(resultSet.getString("source_path"));
                    configuration.setLocation(resultSet.getString("location"));
                    configuration.setFormat(resultSet.getString("format"));
                    configuration.setDateRun(resultSet.getDate("date_run"));
                    configuration.setSeparator(resultSet.getString("separator"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return configuration;
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