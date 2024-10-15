package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//로그인 안한사람이 볼 수 있는 주소 / webjars은 부트스트랩,제이쿼리 같은 프론트 라이브러리를 가르킴/ permitAll 인증받지 않은 사용자 가능하게 하는 메소드
		http.authorizeHttpRequests().antMatchers("/","/auth/**","/js/**","/img/**","/webjars/**").permitAll()
		.anyRequest().authenticated(); // anyRequset ->나머지 요청들은 / authenticated -> 인증 받아야합니다.
		
		http.csrf().disable(); //토큰 비활성화, 개발단계에서 귀찮아서 함 원래는 활성화 후 비교하고 그래야함
		
		http.formLogin().loginPage("/auth/login"); // 권한없는 페이지 방문 시 로그인 페이지로 넘김
		
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
		//로그아웃 요청주소, 로그아웃 한 뒤 url까지 설정
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	// 패스워드 암호화를 편하게 사용하기 위해 생성
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
