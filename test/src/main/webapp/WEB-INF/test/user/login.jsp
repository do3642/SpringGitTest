<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../layout/header.jsp"%>
<h1>${msg }</h1>
<form action="/login" method="post">
	아이디 : <input type="text" name="username"><br>
	비밀번호 : <input type="password" name="password"> <br>
	<input type="submit" value="로그인">
</form>

<%@ include file ="../layout/footer.jsp"%>

