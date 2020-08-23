<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/8 0008
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session</title>
</head>
<body>
<!--
HTTP 协议是无状态的， 即信息无法通过 HTTP 协议本身进传递。 为了跟踪用户的操作状态，
JSP 使用一个叫 HttpSession 的对象实现同样的功能。 HTTPSession 是一个建立在 cookies 和
URL-rewriting 上的高质量的界面。 Session 的信息保存在服务器端， Session 的 id 保存在客户机的
cookie 中。 事实上， 在许多服务器上， 如果浏览器支持的话它们就使用 cookies， 但是如果不支持
或废除了的话就自动转化为 URL-rewriting， session 自动为每个流程提供了方便地存储信息的方法。
Session 一般在服务器上设置了一个 30 分钟的过期时间， 当客户停止活动后自动失效。 Session
中保存和检索的信息不能是基本数据类型如 int， double 等， 而必须是 java 的相应的对象， 如
Integer， Double。
-->
<%
    String sessionId = session.getId();
%>
sessionId=<%=sessionId%><br>

</body>
</html>
