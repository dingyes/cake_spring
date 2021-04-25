<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx }/cake/add" method="post"
		enctype="multipart/form-data">
		重量：<input type="text" name="cakeWeight" /><br> 价格：<input
			type="text" name="cakePrice" /><br> <input type="file"
			name="image" /><br> <input type="submit" value="提交" />
	</form>
</body>
</html>