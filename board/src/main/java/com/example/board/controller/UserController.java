package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.UserService;

@Controller
public class UserController {
 
	@Autowired // 서비스 불러옴
	private UserService userService;
	
	
	@GetMapping("/auth/insertuser")
	public String insertUser() {
		return "user/insertUser";
	}
	
	@PostMapping("/auth/insertuser")
	@ResponseBody // 이게 없으면 jsp파일로 return하기 때문에 넣어야함
	public ResponseDTO<?> insertUser(@RequestBody User user) {
		// 제네릭 부분에 <?> 넣으면 뭐가들어갈지 모름 알아서 넣어라의 기능
		// json으로 받기 위해 @Requsetbody
		
		// 아이디 중복 검사
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
		
		// 클라이언트에게 전달받은 user정보를 서비스로 넘겨줌
		userService.insertUser(user);
		
		// 정상적으로 끝나면 클라이언트한테 회원가입 완료했다고 응답
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername()+"님 회원 가입 성공");
		// OK에서 끝나면 OK를 넣어주고 .value 해야 해당 열거코드를 넣어줌
		}else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername()+ "는 중복된 아이디입니다.");
		}
		
	}
	
//	@PostMapping("/auth/insertuser")
//	public String insertUser(User user) {
////		System.out.println(user);
//		userService.insertUser(user);
//		
//		return "index"; // 그냥 index로 하면 기존 주소가 남기 때문에 redirect로 하는게 좋음
//		
//	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "user/login";
	}
	
	
}
