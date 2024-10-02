package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;

@Controller
public class UserController {
 
	@Autowired // 서비스 불러옴
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
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
	
	@PostMapping("/auth/login")
	@ResponseBody
	public ResponseDTO<?> login(@RequestBody User user, HttpSession session) { // 로그인정보를 담기위해 httpsession 추가
		//없는 아이디 // 요즘은 뭐틀렸는지 안알려줌 보안때문에
		User findUser = userService.getUser(user.getUsername());
		if(findUser.getUsername() == null) {
			//아이디 틀림
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),"없는 아이디");
		}else {
			// 아이디 맞음 -> 비번 검사
			if(findUser.getPassword().equals(user.getPassword())) {
				// 로그인 성공
				session.setAttribute("principal", findUser); // principal 키포지션의 이름은 변수지만 관용임, 로그인정보 담기
				return new ResponseDTO<>(HttpStatus.OK.value(),user.getUsername() + "님 로그인 성공");
			}else {
				// 비번 틀림
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),"비밀번호 틀림");
			} // 안쪽 if문 종료
			
		} // 바깥 else문 종료
	}
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 기존 세션 모든 정보 날리는 메서드
		return "redirect:/";
	}
	
	@GetMapping("/auth/userinfo")
	public String userInfo(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("principal");
//		System.out.println(user);
		
		User userInfo = userRepository.findById(user.getId()).get();
		
		model.addAttribute("userInfo", userInfo);
		
		return "user/userinfo";
	}
	
//	@PostMapping("/auth/update")
//	@ResponseBody
//	public ResponseDTO<?> updateUser(@RequestBody User user, HttpSession session) {
//		User loginUser = (User) session.getAttribute("principal");
//		User userInfo = userRepository.findById(loginUser.getId()).get();
//		
//		
//		
//		userInfo.setPassword(user.getPassword());
//		userInfo.setEmail(user.getEmail());
//		userRepository.save(userInfo);
//			
//		return new ResponseDTO<>(HttpStatus.OK.value(),userInfo.getUsername() + "님 정보 수정 성공");
//		
//	}
	
	@PutMapping("/auth/update")
	@ResponseBody
	public ResponseDTO<?> update(@RequestBody User updateData,HttpSession session) {
//		System.out.println(updateData);
		User userInfo = userRepository.findById(updateData.getId()).get();
		if(!updateData.getPassword().equals(""))
			userInfo.setPassword(updateData.getPassword());
		
		userInfo.setEmail(updateData.getEmail());
		userRepository.save(userInfo);
		
		session.setAttribute("principal", userInfo); // 세션 데이터가 로그인할때만 갱신되기 때문에 새로 갱신 시켜줌
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보 수정 완료");
	}
	
	
	
//	@DeleteMapping("/auth/delete")
//	@ResponseBody
//	public void deleteUser(@RequestBody User deleteUser,HttpSession session) {
//		System.out.println(deleteUser);
////		System.out.println(deleteUser.getId());
//		userRepository.deleteById(deleteUser.getId());
//		session.invalidate();
//	}
	@DeleteMapping("/auth/delete")
	public void deleteUser(Integer user,HttpSession session) {
		System.out.println(user);
//		System.out.println(deleteUser.getId());
//		userRepository.deleteById(id.getId());
		session.invalidate();
	}
		
	
	
	
}
