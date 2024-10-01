package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	public void insertUser(@RequestBody User user) {
		// json으로 받기 위해 requsetbody
		// 클라이언트에게 전달받은 user정보를 서비스로 넘겨줌
		userService.insertUser(user);
		// 정상적으로 끝나면 클라이언트한테 회원가입 완료했다고 응답
		
	}
}
