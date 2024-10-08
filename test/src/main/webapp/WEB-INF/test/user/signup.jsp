<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<form action="/user" method="post">
	아이디: <input id="username"type="text" name="username"> <button id="chk">중복검사</button><br> 
	비밀번호: <input type="password" name="password"><br>
	이메일: <input type="text" name="email">
	<input type="submit" value="회원가입">
</form>

<script src="/js/signup.js"></script>
<%@ include file="../layout/footer.jsp"%>