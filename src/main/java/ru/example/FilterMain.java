package ru.example;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class FilterMain implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init method");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter method");
        Map<String, String[]> map = servletRequest.getParameterMap();
        for(Map.Entry<String, String[]> entry: map.entrySet()){
            System.out.print(entry.getKey()+"  ");
            System.out.println(Arrays.toString(entry.getValue()));
        }

        String user = "test_user";
        String password = "123";
        String url = "jdbc:postgresql://localhost:5432/test";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO requests(date, requestId) VALUES ((?),(?))")) {
            Date date = new Date();
            statement.setDate(2, new java.sql.Date(new Date().getTime()));
            statement.setInt(3, 1);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("Destroy method");
    }
}
