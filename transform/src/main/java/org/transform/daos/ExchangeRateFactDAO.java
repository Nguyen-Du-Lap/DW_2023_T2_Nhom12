package org.transform.daos;

import org.transform.models.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ExchangeRateFactDAO {
    public static int insert(Connection connection) {
        int rowsAffected=0;
        String sql = "INSERT INTO database_warehouse.exchange_rate_fact \n" +
                "                (id_currency, id_name, id_date, exchange_rate, dt_change, dt_expired)  \n" +
                "                SELECT  \n" +
                "                cd.id AS id_currency,  \n" +
                "                nd.id AS id_name,  \n" +
                "                dd.id AS id_date,  \n" +
                "                er.exchange_rate AS exchange_rate,  \n" +
                "                dd.id AS dt_change,  \n" +
                "                1 AS dt_expired  \n" +
                "                FROM database_staging.exchange_rate er  \n" +
                "                JOIN database_warehouse.name_dim nd ON er.name = nd.name  \n" +
                "                JOIN database_warehouse.currency_dim cd ON er.currency = cd.name_currency  \n" +
                "                JOIN database_warehouse.date_dim dd ON er.date = dd.date";
        // Create a PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Execute the query
            rowsAffected = statement.executeUpdate();
            return rowsAffected;
        }catch (SQLException e) {
            return rowsAffected;
        }
    }
}
