<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}${book.bname == null ?'/book/add' : '/book/edit'}" method="post">
	bid:  <input type="text" value="${b.bid}" name="bid"/><br>
	bname:<input type="text" value="${b.bname}" name="bname"/><br>
	price:<input type="text" value="${b.price}" name="price"/><br>
	<button type="submit">确认</button>
</form>
</body>
</html>
