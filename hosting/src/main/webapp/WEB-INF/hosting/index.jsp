<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index page</h1>
	<a href="/product">상품등록</a>
	
	<div>
		<c:forEach var ="item" items="${products}">
			<figure>
				<p><img width="200px" alt="상품이미지" src="${item.imgUrl}"></p>
				<p>제목 : ${item.name}</p>
				<figcaption>설명 : ${item.description}</figcaption>
				<p>가격 : ${item.price}</p>
			</figure>
		</c:forEach>
	</div>

</body>
</html>