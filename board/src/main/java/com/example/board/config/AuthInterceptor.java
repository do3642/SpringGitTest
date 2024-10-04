package com.example.board.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.board.domain.User;

public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인 했는지 안했는지 확인할 세션 호출
		HttpSession session = request.getSession();
		// request안에 session객체를 호출하는 메서드가 있음 
		
		// 세션은 모든 데이터타입을 받아들이기 때문에 오브젝트 타입임
		// 그래서 하위클래스인 User를 생으로 못넣음 (자식안에 부모를 넣는거라)
		// 그래서 User로 형변환 해줘야함
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			response.sendRedirect("/auth/login");
		}
		
		return true;
	}

	
}
