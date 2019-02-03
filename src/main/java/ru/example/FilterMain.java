package ru.example;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
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
        System.out.println(servletRequest.getLocalAddr());
        System.out.println(servletRequest.getProtocol());
        System.out.println(servletRequest.getServerName());
        System.out.println(servletRequest.getServerPort());
        System.out.println("-------------------------------------------------------------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("Destroy method");
    }
}
