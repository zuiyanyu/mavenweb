<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>论坛首页</title>
</head>
<body>
    <%--${loginUser}<br/>--%>
    ${loginUser.userName}，欢迎您进入我的论坛首页，您当前积分为 ${loginUser.credits} ;

</body>
</html>
