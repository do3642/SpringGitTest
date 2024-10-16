<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <%@ include file="../layout/header.jsp" %>
  
  <div class="container mt-3">
   <form method="post" action="/auth/login">
     <div class="mb-3 mt-3">
       <label for="username" class="form-label">userName:</label>
       <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
     </div>
     <div class="mb-3">
       <label for="password" class="form-label">Password:</label>
       <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
     </div>
     <%-- <input type="text" name="${_csrf.parameterName }" value="${_csrf.token }"> --%>
     <button id="btn-login" type="submit" class="btn btn-primary">로그인</button>
   </form>
   <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=7b9c00e8391bcd0e9a9169bd52896a6e&redirect_uri=http://localhost:8888/oauth/kakao">
   		<img src="/img/kakao_login-btn.png">
   </a>
</div>
<!--  <script src="/js/login.js"></script> -->
  <%@ include file="../layout/footer.jsp" %>