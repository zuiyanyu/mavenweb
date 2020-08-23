<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>jspuseBean</title>
</head>
<body>
<jsp:useBean id="student" scope="page" class="jsp.beans.Student" />
<%=student.getName()%><br>
<% student.setName("wll"); %><br>

<%=student.getName()%><br>
</body>
</html>
