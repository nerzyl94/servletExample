package ru.example;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ConnectionPSQL {
    private String USER = "postgres";
    private String PASSWORD = "123";
    private String URL = "jdbc:postgresql://localhost:5432/requests";

    public static void main(String[] args) {
        ConnectionPSQL connectionPSQL = new ConnectionPSQL();
        Map<Integer, LocalDate> requests = connectionPSQL.getRequests();
        Map<Integer, String> params = connectionPSQL.getParams(requests);
        System.out.println(requests);
        System.out.println(params);
    }

    public Map<Integer, LocalDate> getRequests() {
        Map<Integer, LocalDate> map = new TreeMap<>();
        try(Connection connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD)){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT request_id, request_date from request"
            );
            ResultSet resultSet = statement.executeQuery();
            if(resultSet != null){
                while(resultSet.next()){
                    map.put(resultSet.getInt(1), resultSet.getDate(2).toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<Integer, String> getParams(Map<Integer, LocalDate> map){
        Map<Integer, String> result = new TreeMap<>();
        try(Connection connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD)){
            for (Map.Entry<Integer, LocalDate> entry : map.entrySet()){
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT param, value from params where request_id = (?)"
                );
                statement.setInt(1, entry.getKey());

                ResultSet resultSet = statement.executeQuery();
                if(resultSet != null){
                    String record = "";
                    while(resultSet.next()){
                        record += "Param: " + resultSet.getString(1) + " Value: " +
                                resultSet.getString(2) + "\n";
                        result.put(entry.getKey(), record);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeParams(Map<String, String[]> map) throws SQLException {

        int requestId = (int)new java.util.Date().getTime();

        try(Connection connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD)){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO request (request_date, request_id) VALUES (?, ?)");

            java.sql.Date ourJavaDateObject = new java.sql.Date(new java.util.Date().getTime());
            statement.setDate(1, ourJavaDateObject);
            statement.setInt(2, requestId);
            statement.executeUpdate();

            for(Map.Entry<String, String[]> entry: map.entrySet()){
                if(entry.getKey().equals("submit")){
                    continue;
                }
                PreparedStatement statement2 = connection.prepareStatement(
                        "INSERT INTO params (request_id, param, value) values (?, ?, ?)"

                );
                statement2.setInt(1, requestId);
                statement2.setString(2, entry.getKey());
                statement2.setString(3, Arrays.toString(entry.getValue()));
                statement2.executeUpdate();
            }
        }
    }
}

