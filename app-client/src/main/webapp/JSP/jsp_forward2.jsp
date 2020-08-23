<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/8 0008
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>被跳转到此页面</title>
</head>
<body BGCOLOR="#FFFFFF">
<% String contxtPath = pageContext.getServletContext().getContextPath() ;%>
pageContext.getServletContext().getContextPath() = <%=contxtPath %><br>

<%out.print("中文");%>
</body>
</html>
