package ru.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPSQL connectionPSQL = new ConnectionPSQL();
        Map<Integer, LocalDate> requests = connectionPSQL.getRequests();
        Map<Integer, String> params = connectionPSQL.getParams(requests);
        String table = "";
        int i = 0;
        for (Map.Entry entry: requests.entrySet()){
            table+= "<tr><td>" + entry.getKey()+ "</td><td>" + entry.getValue() +"</td><td>"+
                    params.get(entry.getKey())+"</td></tr>";
        }

        String s = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
                "<title>Requests</title>" +
                "</head>" +
                "<body>" +
                "<table border='2px' >" +
                    "<tr>" +
                        "<td>Request Id</td>" +
                        "<td>Request Data</td>" +
                        "<td>Params + Values</td>" +
                    "</tr>" + table +
                "</table>" +
                "</body>" +
                "</html>";
        resp.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
