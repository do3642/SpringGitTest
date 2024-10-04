<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./layout/header.jsp" %>


	 <div class="container mt-3">
	 	<c:forEach var ="post" items="${postList}"><!--var는 작명의 영역/변수명임 -->
      <div class="card">
         <div class="card-body">
             <h4 class="card-title">${post.title}</h4>
            <a href="#" class="btn btn-secondary">상세보기</a>
         </div>
      </div>
      </c:forEach>
   </div>
   
<%@ include file="./layout/footer.jsp" %>