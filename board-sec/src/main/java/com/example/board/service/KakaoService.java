package com.example.board.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.google.gson.Gson;

@Service
public class KakaoService {
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	public String getAccessToken(String code) {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//카카오 로그인 요청-본문 항목에 있음
		//스프링 컬렉션이고 하나의 키에 여러개의 벨류 세팅이 가능함 요청,응답할때 주로 사용됨
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "7b9c00e8391bcd0e9a9169bd52896a6e");
		body.add("redirect_uri", "http://localhost:8888/oauth/kakao");
		body.add("code", code);
		
		//제네릭은 body에 대한 제네릭, header은 httpheaders가 알아서 키,벨류값으로 가지고 있음
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body,header);
		RestTemplate restTemplate = new RestTemplate();
		
		
		ResponseEntity<String> responseEntity =restTemplate.exchange(
					"https://kauth.kakao.com/oauth/token", //요청주소 (url)
					HttpMethod.POST, //요청 메소드 
					requestEntity, // 요청 객체
					String.class // 응답받을 데이터 타입
				);
		
		String jsonData = responseEntity.getBody();
		Gson gson = new Gson();
		Map<?, ?> data =gson.fromJson(jsonData, Map.class);
		
		System.out.println(data);
		
		return (String) data.get("access_token");
	}
	
	public User getUserInfo(String accessToken) {
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + accessToken);
		// Bearer 앞에 띄어쓰기 하나 있어야함
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//요청객체
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					requestEntity,
					String.class
				);
				
		String jsonData = responseEntity.getBody();
		Gson gson = new Gson();
		Map<?, ?> data =gson.fromJson(jsonData, Map.class);
		
		//응답 데이터에서 닉네임을 추출하기 위한 처리
		Map<?, ?> properties = (Map<?,?>) data.get("properties");
		String nickname = (String)properties.get("nickname");
		//응답 데이터에서 이메일을 추출하기 위한 처리
		Map<?, ?> kakao_account = (Map<?,?>) data.get("kakao_account");
		String email = (String)kakao_account.get("email");
		
		User user = new User();
		user.setUsername(nickname);
		user.setEmail(email);
		user.setPassword(kakaoPassword);
		user.setRole(RoleType.USER);
		
		
		
		return user;
	}
}
