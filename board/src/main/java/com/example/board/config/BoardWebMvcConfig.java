package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BoardWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 객체를 add..()안에 넣어주면됨 그래서 new로 생성자호출
		registry.addInterceptor(new AuthInterceptor())
		// 이렇게 마무리하면 매번 실행되기 때문에
		.addPathPatterns("/","/post/**"); // 이렇게 요청했을때 에드 인터셉터 실행해줘
		// 에드패턴으로 페이지 지정, auth로 시작하는 모든 경로에 인터셉터 걸려면 /auth/** 주소로 하면됨
		// *은 바로 하위주소까지만, **은 모든 하위주소 포함
		
		
		
	}

}
