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
	<form action="${ctx }/cake/update" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="${update.id }" /><br> <input
			type="hidden" name="pic" value="${update.pic }"> 重量：<input
			type="text" name="cakeWeight" value="${update.weight }" /><br>
		价格：<input type="text" name="cakePrice" value="${update.price }" /><br>
		图像：<img src="/image/${update.pic }" width="80" height="80" /><br>
		<input type="file" name="image"><br> <input type="submit"
			value="更新" />
	</form>
</body>
</html>