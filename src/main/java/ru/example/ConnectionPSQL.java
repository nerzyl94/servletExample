package ru.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConnectionPSQL {

    public static void main(String[] args) throws SQLException {
        String USER = "test_user";
        String PASSWORD = "123";
        String URL = "jdbc:postgresql://localhost:5432/test";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO requests (dateRequest) VALUES (?)")) {
            Long date = new java.util.Date().getTime();
            statement.setDate(1, new Date(date));
            statement.executeUpdate();

        } finally {
            connection.close();
        }

    }


}
