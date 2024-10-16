package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.User;
import com.example.board.service.KakaoService;
import com.example.board.service.UserService;

@Controller
public class KakaoController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	
	
	@GetMapping("/oauth/kakao")
	public String kakaoCallBack(String code) {
		
		String accessToken = kakaoService.getAccessToken(code);
		User kakaoUser = kakaoService.getUserInfo(accessToken);
		
		//kakaoUser정보가 DB에 있는지 체크 / 서비스에 하는게 나은데 헷갈리니 여기함
		User findUser = userService.getUser(kakaoUser.getUsername());
		
		//DB에서 검색한 findUser에 내용이 없으면 회원가입 처리
		//내용이 있으면 이미 있는 사람이므로 넘어감
		if(findUser.getUsername() == null)
			userService.insertUser(kakaoUser);
		
		//로그인 처리
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoPassword);
		
		Authentication authentication = authenticationManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return "redirect:/";
	}
	
}
