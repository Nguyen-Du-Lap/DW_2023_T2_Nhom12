package org.transform.daos;

import org.transform.models.Configuration;

import java.sql.*;

public class ExchangeRateFactDAO {
    public static int insert(Connection connection) {
        int rowsAffected = 0;
        String sql = "{CALL InsertExchangeRateFact()}"; // Gọi stored procedure InsertExchangeRateFact

        try (CallableStatement statement = connection.prepareCall(sql)) {
            rowsAffected = statement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return rowsAffected;
        }
    }

}
