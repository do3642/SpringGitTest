<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container border py-3 mt-3">
	<c:if test="${empty post}">
		<h1>등록된 게시물이 없습니다.</h1>
	</c:if>
   <div>
      <h3>${post.title }</h3>
   </div>
   <div>
      <p>${post.content }</p>
   </div>
   <div>
      <p>포스트 번호 : <span id="id">${post.id }</span></p>
      <p>작성자 : ${post.user.username }</p>
   </div>
   <hr>
   <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
   <a href="/post/modify/${post.id}" class="btn btn-warning">수정하기</a>
   <button id="btn-delete" class="btn btn-danger">삭제하기</button>
</div>

<script src="/js/post.js"></script>
<%@ include file="../layout/footer.jsp" %>