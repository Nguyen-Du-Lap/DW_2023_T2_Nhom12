package org.transform.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExchangeRateDAO {
    public static int truncate(Connection connection) {
        String truncateQuery = "TRUNCATE TABLE exchange_rate";
        try (PreparedStatement preparedStatement = connection.prepareStatement(truncateQuery)) {
            // Execute the truncate query
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
