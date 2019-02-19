package ru.example;

import java.sql.*;
import java.util.Calendar;

public class ConnectionPSQL {

    public static void main(String[] args) throws SQLException {
        String USER = "postgres";
        String PASSWORD = "123";
        String URL = "jdbc:postgresql://localhost:5432/requests";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO request (request_date, request_id) VALUES (?, ?)")) {

            java.sql.Date ourJavaDateObject = new java.sql.Date(new java.util.Date().getTime());
            statement.setDate(1, ourJavaDateObject);
            statement.setInt(2, 2);
            statement.executeUpdate();

        } finally {
            connection.close();
        }

    }


}
