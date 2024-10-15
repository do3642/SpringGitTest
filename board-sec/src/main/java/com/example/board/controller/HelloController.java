package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	@GetMapping("/html")
	public String html() {
		System.out.println("html 요청받음");
		return "redirect:hello.html";
	}
	
	@GetMapping("/text")
	@ResponseBody
	public String text() {
		return "hello text";
//		jsp파일로 인식하기 때문에 그냥 적는건 안된다 
		// 그래서 reponseBody 어노테이션 해야 글자로 들어감
		// Respon... 쓰면 json형태로 리턴시킴
	}
	
	//  /img로 get요청을 하면 정적폴더에 있는 해당 이미지가 리다이렉트 되도록 구현
	
	@GetMapping("/img")
	public String img() {
		return "redirect:img/pepe.jpg";
	}
	
	@GetMapping("/jsp")
	public String jsp(Model model) {
//		model.addAttribute(변수명, 값);
		model.addAttribute("name", "홍길동");
		
		return "hello";
		// 아까 만든 jsp파일명 입력하면됨, 아까 프롬퍼티?에서 만든 /WEB-INF/board 랑 .jsp 로 작성해서 
		// 기본값으로 딸려들어감
		
	}
	
	
}
