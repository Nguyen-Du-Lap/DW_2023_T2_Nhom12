package org.transform.daos;

import org.transform.models.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ExchangeRateFactDAO {
    public static int insert(Connection connection, Date date) {
        int rowsAffected=0;
        String sql = "INSERT INTO database_warehouse.exchange_rate_fact " +
                "(id_currency, id_name, id_date, exchange_rate, dt_change, dt_expired) " +
                "SELECT " +
                "cd.id AS id_currency, " +
                "nd.id AS id_name, " +
                "dd.id AS id_date, " +
                "er.exchange_rate AS exchange_rate, " +
                "dd.id AS dt_change, " +
                "1 AS dt_expired " +
                "FROM database_staging.exchange_rate er " +
                "JOIN database_warehouse.name_dim nd ON er.name = nd.name " +
                "JOIN database_warehouse.currency_dim cd ON er.currency = cd.name_currency " +
                "JOIN database_warehouse.date_dim dd ON er.date = dd.date " +
                "WHERE er.date = ?";
        // Create a PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, date);
            // Execute the query
            rowsAffected = statement.executeUpdate();
            return rowsAffected;
        }catch (SQLException e) {
            return rowsAffected;
        }
    }
}
