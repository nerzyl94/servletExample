package ru.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/about")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstValue = req.getParameter("first");
        System.out.println(firstValue);
        String secondValue = req.getParameter("second");
        System.out.println(secondValue);
        PrintWriter pw = resp.getWriter();
        String s = "<html>" +
                "<head>" +
                "<title>Servlet</title>" +
                "</head>" +
                "<body>" +
                "<form action = 'about' method='post'>" +
                "<input type='text' name='first'>" +
                "<input type='text' name='second'>" +
                "<input type = 'submit' name = 'submit' value = 'Submit'>" +
                "</form>" +
                "First = " + firstValue + " Second = " + secondValue +
                "</body>" +
                "</html>";
        pw.write(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

