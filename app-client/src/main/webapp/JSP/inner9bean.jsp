<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/8 0008
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP 9 种基本内置组件</title>
</head>
<body>
<center>
    <h3>
    request 用户端请求， 此请求会包含来自 GET/POST 请求的参数 <br>
    response 网页传回用户端的回应<br>
    pageContext 网页的属性是在这里管理<br>
    session 与请求有关的会话期<br>
    application Servlet 正在执行的内容<br>
    out 用来传送回应的输出<br>
    config Servlet 的构架部件<br>
    page JSP 网页本身<br>
    exception 针对错误网页， 未捕捉的例外<br>
    </h3>
</center>

out.print("hello world"):  <%out.print("hello world");%> <br>
String name=request.getParameter("name"):  <% String name=request.getParameter("name"); out.print(name);%> <br>
</body>
</html>
