<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet</title>
</head>
<body>
    <form action = 'main' method='post'>
        <input type='text' name='first'>
        <input type='text' name='second'>
        <input type = 'submit' name = 'submit' value = 'Submit'>
    </form>
    <%
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        if (first != null && second != null) {
    %>
    First = <%=first%>, Second = <%=second%>
    <% }
    %>

    <form action = 'requests' method = 'get'>
    <button>Show all requests</button>
    </form>
</body>
</html>