package com.example.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.service.PostService;



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
	
	@GetMapping({"","/"}) // 이런식으로 괄호로 다수에 걸 수 있음
	public String getPostList(Model model,@PageableDefault(size =10, sort = "id", direction = Direction.DESC) Pageable pageable) { // pageable,direction import는 data.domain으로해야함
		//페이지 에이블 기본값 지정하는 어노테이션 size- 페이지갯수, sort 정렬(id는 게시판번호),direction은 오름,내림 설정
		
		
		Page<Post> postList = postService.getPostList(pageable);
		// 페이지 갯수를 db 모든 데이터를 뽑아오는 서비스에 보내줘서 10개씩 데이터를 가져오게끔 설정해줘야함
		
		// html로 데이터를 보내기 위한 모델객체
		model.addAttribute("postList", postList);
		
		return "index";
	}
	
	
	
}
