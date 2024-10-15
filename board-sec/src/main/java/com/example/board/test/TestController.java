package com.example.board.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.dto.UserDTO;

@Controller
public class TestController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/jointest")
	public String test() {
		return "test/join";
	}
	
	@PostMapping("/test/join")
	public String join(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			//입력한게 문제가 있으니 오류메세지 담아서 보내줌
			// 오류 메세지를 담아줄 컬렉션을 생성
			List<String> errorMsg = new ArrayList<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMsg.add(error.getDefaultMessage());
			}
			model.addAttribute("errorMsg", errorMsg);
			//회원가입 실패해도 기존 입력내용 남기기 위해 모델에 저장
			model.addAttribute("userDTO",userDTO);
			//실패했으니 회원가입페이지로 다시 리턴
			return "test/join";
		}else {
			return "redirect:/";
		}
		
	}
	
}
