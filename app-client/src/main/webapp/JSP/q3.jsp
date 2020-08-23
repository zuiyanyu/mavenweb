<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user like ?</title>
</head>
<body>
    <%! String food=""; %>
    <%
        String userName = (String) session.getAttribute("userName");
        food = request.getParameter("food");
    %>
    您的姓名是： <%= userName %>
    <P>您喜欢吃： <%= food %>
</BODY>
</html>
