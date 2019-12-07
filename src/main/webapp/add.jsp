<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/13
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="color: red">${message}</div>
<form action="${pageContext.request.contextPath}/add" method="post">
    帐号：<input type="text" name="username1"><br>
    密码：<input type="password" name="password1"><br>
    <input type="submit" value="注册">
    <input type="reset" value="重置">
</form>
</body>
</html>