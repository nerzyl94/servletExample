package ru.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstValue = req.getParameter("first");
        System.out.println(firstValue);
        String secondValue = req.getParameter("second");
        System.out.println(secondValue);
        PrintWriter pw = resp.getWriter();
        String s = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
                "<title>Servlet</title>" +
                "</head>" +
                "<body>" +
                "<form action = 'main' method='post'>" +
                "<input type='text' name='first'>" +
                "<input type='text' name='second'>" +
                "<input type = 'submit' name = 'submit' value = 'Submit'>" +
                "</form>" +
                "First = " + firstValue + " Second = " + secondValue +
                "<form action = 'requests' method = 'get'>" +
                "<button>Show all requests</button>" +
                "</form>" +
                "</body>" +
                "</html>";
        pw.write(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

