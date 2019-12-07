<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/wxm" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>小说目录</h2>
	<br>

	<form action="${pageContext.request.contextPath}/book/list"
		method="post">
		书名：<input type="text" name="bname"> <input type="submit"
			value="确定">
			<input type="hidden" name="rows" value="15">
	</form>
	<a href="${pageContext.request.contextPath}/book/preSave">增加</a>
	<table border="1" width="100%">
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>价格</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${bookList }" var="b">
			<tr>
				<td>${b.bid }</td>
				<td>${b.bname }</td>
				<td>${b.price }</td>
				<td>
					<a href="${pageContext.request.contextPath}/book/preSave?bid=${b.bid}">修改</a>&nbsp;
					<a href="${pageContext.request.contextPath}/book/del/${b.bid}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<z:page pageBean="${pageBean }"></z:page>
</body>
</html>
