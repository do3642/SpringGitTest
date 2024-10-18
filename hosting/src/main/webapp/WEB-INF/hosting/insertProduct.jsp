<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품등록 페이지</h1>
	<form id="product-form" action="/product" method="POST">
		상품명 : <input type="text" name="name"><br>
		상품설명 : <input type="text" name="description"><br>
		가격 : <input type="text" name="price"><br>
		상품 이미지 : <input id="img-file" type="file"><br>
		<!-- 아래 인풋은 원래 hidden으로 설정해야함-->
		<input id="imgUrl" type="text" name="imgUrl"><br>
		<input type="submit" value="상품등록">
	</form>

<script src="/js/product.js">?</script>
</body>
</html>