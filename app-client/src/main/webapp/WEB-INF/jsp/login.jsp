<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>论坛登录页面</title>
    <%--login.jsp 有两个用处：1.作为登录页面。 2.作为登录失败后的相应页面--%>
    <%--login.jsp放置在 WEB-INF/jsp目录下，无法直接通过URL进行调用，它由LoginController控制类中 loginPage()进行转发--%>
</head>
<body>

    <%--登录失败的错误信息--%>
    <c:if test="${!empty error}">
        <font color ="red"><c:out value="${error}"/> </font>
    </c:if>

    <%--JSTL标签会在URL前自动加上应用程序部署根目录--%>
    <form action="<c:url value="/loginCheck.html"></c:url> " method="post">
        用户名：<input type="text" name="userName"><br>
        密码：<input type="password" name="password" ><<br>
        <input type="submit" value="登录" > <input type="reset" value="重置">
    </form>
</body>
</html>
