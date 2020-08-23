<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/8 0008
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP中获取各种CGI环境变量</title>
</head>
<body>
<table border=1 cellspacing=0 cellpadding=0 align=center>
    <tr>
        <th>Name</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>request.getCharacterEncoding()&nbsp;&nbsp;CharacterEncoding</td>
        <td>&nbsp;<%=request.getCharacterEncoding()%></td>
    </tr>
    <tr>
        <td>request.getContentLength()&nbsp;&nbsp;CONTENT_LENGTH</td>
        <td>&nbsp;<%=request.getContentLength()%></td>
    </tr>
    <tr>
        <td>request.getContentType()&nbsp;&nbsp;CONTENT_TYPE</td>
        <td>&nbsp;<%=request.getContentType()%></td>
    </tr>
    <tr>
        <td>request.getProtocol()&nbsp;&nbsp;SERVER_PROTOCOL</td>
        <td>&nbsp;<%=request.getProtocol()%></td>
    </tr>
    <tr>
        <td>request.getRemoteAddr()&nbsp;&nbsp;REMOTE_ADDR</td>
        <td>&nbsp;<%=request.getRemoteAddr()%></td>
    </tr>
    <tr>
        <td>request.getRemoteHost()&nbsp;&nbsp;REMOTE_HOST</td>
        <td>&nbsp;<%=request.getRemoteHost()%></td>
    </tr>
    <tr>
        <td>request.getServerPort()&nbsp;&nbsp;SERVER_PORT</td>
        <td>&nbsp;<%=request.getServerPort()%></td>
    </tr>
    <tr>
        <td>request.getScheme()&nbsp;&nbsp;Scheme</td>
        <td>&nbsp;<%=request.getScheme()%></td>
    </tr>
    <tr>
        <td>request.getServerName()&nbsp;&nbsp;SERVER_NAME</td>
        <td>&nbsp;<%=request.getServerName()%></td>
    </tr>
    <tr>
        <td>request.getAuthType()&nbsp;&nbsp;AUTH_TYPE</td>
        <td>&nbsp;<%=request.getAuthType()%></td>
    </tr>
    <tr>
        <td>request.getMethod()&nbsp;&nbsp;REQUEST_METHOD</td>
        <td>&nbsp;<%=request.getMethod()%></td>
    </tr>
    <tr>
        <td>request.getPathInfo()&nbsp;&nbsp;PATH_INFO</td>
        <td>&nbsp;<%=request.getPathInfo()%></td>
    </tr>
    <tr>
        <td>request.getPathTranslated()&nbsp;&nbsp;PATH_TRANSLATED</td>
        <td>&nbsp;<%=request.getPathTranslated()%></td>
    </tr>
    <tr>
        <td>request.getQueryString()&nbsp;&nbsp;QUERY_STRING</td>
        <td>&nbsp;<%=request.getQueryString()%></td>
    </tr>
    <tr>
        <td>request.getRemoteUser()&nbsp;REMOTE_USER</td>
        <td>&nbsp;<%=request.getRemoteUser()%></td>
    </tr>
    <tr>
        <td>request.getRequestURI()request.getRequestURI()&nbsp;&nbsp;REQUEST_URI</td>
        <td>&nbsp;<%=request.getRequestURI()%></td>
    </tr>
    <tr>
        <td>request.getServletPath()&nbsp;&nbsp;SCRIPT_NAME</td>
        <td>&nbsp;<%=request.getServletPath()%></td>
    </tr>
</table>

=====================================================<br>
<%
    out.println("request.getProtocol() Protocol: " + request.getProtocol() + "<br>");
    out.println("request.getScheme() Scheme: " + request.getScheme() + "<br>");
    out.println(" request.getServerName() Server Name: " + request.getServerName() + "<br>" );
    out.println("request.getServerPort() Server Port: " + request.getServerPort() + "<br>");
    out.println("getServletConfig().getServletContext().getServerInfo() Server Info: " + getServletConfig().getServletContext().getServerInfo() + "<br>");
    out.println(" request.getRemoteAddr()  Remote Addr: " + request.getRemoteAddr() + "<br>");
    out.println("request.getRemoteHost() Remote Host: " + request.getRemoteHost() + "<br>");
    out.println("request.getCharacterEncoding() Character Encoding: " + request.getCharacterEncoding() + "<br>");
    out.println("request.getContentLength() Content Length: " + request.getContentLength() + "<br>");
    out.println("request.getContentType()  Content Type: "+ request.getContentType() + "<br>");
    out.println("request.getAuthType() Auth Type: " + request.getAuthType() + "<br>");
    out.println("request.getMethod() HTTP Method: " + request.getMethod() + "<br>");
    out.println("request.getPathInfo() Path Info: " + request.getPathInfo() + "<br>");
    out.println("request.getPathTranslated() Path Trans: " + request.getPathTranslated() + "<br>");
    out.println("request.getQueryString() Query String: " + request.getQueryString() + "<br>");
    out.println("request.getRemoteUser()  Remote User: " + request.getRemoteUser() + "<br>");
    out.println("request.getRequestedSessionId() Session Id: " + request.getRequestedSessionId() + "<br>");
    out.println("request.getRequestURI() Request URI: " + request.getRequestURI() + "<br>");
    out.println("request.getServletPath() Servlet Path: " + request.getServletPath() + "<br>");
    out.println("request.getHeader(\"Accept\") Accept: " + request.getHeader("Accept") + "<br>");
    out.println("request.getHeader(\"Host\") Host: " + request.getHeader("Host") + "<br>");
    out.println("request.getHeader(\"Referer\") Referer : " + request.getHeader("Referer") + "<br>");
    out.println("request.getHeader(\"Accept-Language\") Accept-Language : " + request.getHeader("Accept-Language") +"<br>");
    out.println("request.getHeader(\"Accept-Encoding\") Accept-Encoding : " + request.getHeader("Accept-Encoding") +"<br>");
    out.println("request.getHeader(\"User-Agent\") User-Agent : " + request.getHeader("User-Agent") + "<br>");
    out.println("request.getHeader(\"Connection \")  Connection : " + request.getHeader("Connection ") +"<br>");
    out.println("session.getCreationTime() Created : " + session.getCreationTime() + "<br>");
    out.println("session.getLastAccessedTime() LastAccessed : " + session.getLastAccessedTime() + "<br>");
%>
</body>
</html>
