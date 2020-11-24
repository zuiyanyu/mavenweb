<%@ page import="jsp.beans.Circle" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/7 0007
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp语法声明</title>
</head>
<body>
<header><h2>jsp语法声明 </h2></header>
<%! int i = 0; %>
<%! int a ,b,c ;%>
<%! Circle circle = new Circle(2.0); %>
描述：
声明你将要在 JSP 程序中用到的变量和方法。 你也必须这样做， 不然会出错。
你可以一次性声明多个变量和方法， 只要以";"结尾就行， 当然这些声明在 Java 中要是合法的。 当
你声明方法或变量时， 请注意以下的一些规则：
声明必须以";"结尾(Scriptlet 有同样的规则， 但是 表达式就不同了).
你可以直接使用在《% @ page %>中被包含进来的已经声明的变量和方法， 不需要对它们重新进行
声明。 一个声明仅在一个页面中有效。 如果你想每个页面都用到一些声明， 最好把它们写成一个单
独的文件， 然后用    <\%@ include %> 或 <\%jsp:include %> 元素包含进来
<h> ${2+'4'}</h>
</body>
</html>
