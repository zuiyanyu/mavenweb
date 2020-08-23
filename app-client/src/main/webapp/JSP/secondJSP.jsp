<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP表达式</title>
</head>
<body>
<%-- 声明语句--%>
<%! Map<String,String> map = new HashMap<String,String>();%>

<%-- 代码块 使用上面声明的语句--%>
<%
    map.put("a", "1");
    map.put("b","2");
    //注释
    /* 注释*/
    Map<String,String> map2 = new HashMap<String,String>();
    map2.put("a", "1");

%>

<%-- 表达式--%>
<font color = "blue" > map.size() = <%= map.size() %></font><br>
<font color = "blue" > map2.size() = <%= map2.size() %></font>



<%@page session="true" %>
session=”true | false”。 如果值为“true”（ 缺省） 表示： 预定义变量 session（ 继承HttpSession）
应该绑定到一个已存在的 session， 否则就应该创建一个并将之绑定。 值为“false” 时表示： 将不
使 用 session 变 量 ， 如 果 试 图 使 用 ， 将 在 JSP 向 Servlet 转 化 时 出 现 错 误 。
<% 
    if(session.getValue("name")==null)
         session.putValue("name","123456");
    else
        session.putValue("name",session.getValue("name")+"1");
%>
<% out.println(session.getValue("name"));%>

errorPage = “ URL”。 指定一个 JSP mso-hansi-font-family:""＞ 页面来处理任何一个可抛出
的但当前页面并未处理的意外错误。 如：
<%@page errorPage="errorPage.jsp"%>
<%!int i=0;%>
<%=7/i%>

</body>
</html>
