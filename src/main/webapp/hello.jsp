<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>主界面<%=System.currentTimeMillis()%>，欢迎您：[${sessionScope.username}]</h1>

<ul>
    shiro注解
    <li>
        <a href="${pageContext.request.contextPath}/passUser">用户认证</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/passRole">角色</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/passPer">权限认证</a>
    </li>
</ul>
</body>
</html>



