<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>saveNameToSession</title>
</head>
<body>
<%! String userName = "" ; %>
<%
  userName = request.getParameter("userName") ;
  session.setAttribute("userName",userName);
%>
<h1><%=userName%></h1>欢迎登陆系统！

    <FORM METHOD=POST ACTION="q3.jsp">
        <p>您喜欢吃什么 ?<P><INPUT TYPE=TEXT NAME="food">
    <INPUT TYPE=SUBMIT VALUE="SUBMIT">
    </FORM>
</body>
</html>
