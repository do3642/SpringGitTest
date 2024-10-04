package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.service.PostService;
import com.example.board.service.UserService;


@Controller
public class PostController {

	@Autowired // 서비스 불러옴
	private PostService postService;
	@Autowired // 서비스 불러옴
	private PostRepository postRepository;
	
	@GetMapping("/post")
	public String insertPost() {

		return "post/insertPost";
	}
	
	@PostMapping("/post")
	@ResponseBody
	public ResponseDTO<?> insertUser(@RequestBody Post post,HttpSession session) {
//		System.out.println("포스트컨트롤러");
//		
//		User user = (User) session.getAttribute("principal");
//		System.out.println(user.getUsername());
//		
//		post.setUser(user);
//		
//		postService.insertPost(post);
//		return new ResponseDTO<>(HttpStatus.OK.value(),"게시물 등록 완료");
//				
		//선생님 풀이
		
		// 세션에 있는 유저 정보를 추출
		User principal = (User) session.getAttribute("principal");
		
		postService.insertPost(post,principal);
		
		// 저장이 끝나면 결과를 응답
		return new ResponseDTO<>(HttpStatus.OK.value(),"게시물 등록 완료");
		
				
	}
	
	
	
}
