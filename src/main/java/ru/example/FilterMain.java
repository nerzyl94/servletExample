package ru.example;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class FilterMain implements Filter {

    public void init(FilterConfig filterConfig) {
        System.out.println("init method");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter method");

        if(!servletRequest.getParameterMap().isEmpty()){
            Map<String, String[]> map = servletRequest.getParameterMap();
            try {
                ConnectionPSQL connectionPSQL = new ConnectionPSQL();
                connectionPSQL.writeParams(map);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("Destroy method");
    }
}
