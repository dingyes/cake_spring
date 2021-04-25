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
	<table border="1" cellpadding="10" cesllspacing="0">
		<tr>
			<td>编号</td>
			<td>重量</td>
			<td>价格</td>
			<td>图片</td>
			<td>操作</td>
			<td>操作</td>
		</tr>
		<c:forEach var="cake" items="${cakes }">
			<tr>
				<td>${cake.id }</td>
				<td>${cake.weight }</td>
				<td>￥${cake.price }</td>
				<td><img src="/image/${cake.pic }" width="80" height="80"/></td>
				<td><a href="${ctx }/cake/update/${cake.id}">修改</a></td>
				<td><a href="${ctx }/cake/delete/${cake.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>