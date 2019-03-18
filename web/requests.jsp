<%@ page import="ru.example.ConnectionPSQL" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>List of Requests:</h2>
<table border='2px'>
    <tr>
        <td>Request Id</td>
        <td>Request Data</td>
        <td>Params + Values</td>
    </tr>
    <% ConnectionPSQL connectionPSQL = new ConnectionPSQL();
    Map<Integer, LocalDate> requests = connectionPSQL.getRequests();
    Map<Integer, String> params = connectionPSQL.getParams(requests);

    for (Map.Entry entry: requests.entrySet()){ %>
     <tr>
         <td><%=entry.getKey()%></td>
         <td><%=entry.getValue()%></td>
         <td><%=params.get(entry.getKey())%></td>
     </tr>
<%  }
%>
</table>


</body>
</html>
