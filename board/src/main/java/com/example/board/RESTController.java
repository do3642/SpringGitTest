package com.example.board;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") // 웹 표준에서 막아둔 제한 해제 ex) 포트 번호의 다름(vscode라이브는 5500포트 여기환경은 8888)
@RestController
public class RESTController {
	
	
	@GetMapping("/rest")
	public String get() {
		
		return "get 요청 처리";
	}
	
	@PostMapping("/rest")
	public String post() {
		return "post 요청 처리";
	}
	
	@PutMapping("/rest")
	public String put() {
		return "put 요청 처리";
	}
	
	@DeleteMapping("/rest")
	public String delete() {
		return "delete 요청 처리";
	}
	
	@GetMapping("/test")
	public List<TestVO> getTest() {
		System.out.println("test요청 받음");
		TestVO vo = new TestVO("홍길동","1234");
		TestVO vo2 = new TestVO("고길동","qwer");
		TestVO vo3 = new TestVO("도우너","pppp");
		
		List<TestVO> list = new ArrayList<>();
		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		
		return list;
	}
	
	// 클라이언트에서 보낸 아이디 패스워드 받기 방법1
	// html에 form태그로 데이터를 전송할 경우
	@PostMapping("/test")
	public String postTest(TestVO vo) {
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		// 객체로 매개변수를 받을 때 html에서 form태그 안에 input 태그로 받을 수 있고 name=""이 멤버변수와 명이 같아야함
		return "정상 처리";
	} // 이건 기본 생성자 없이 처리됨
	
	// json으로 데이터를 전송
	@PostMapping("/test2")
	public String postTest2(@RequestBody TestVO vo) {
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		return "정상 처리";
	} // 기본 생성자를 생성 후 객체를 생성해 값을 넣는 방식이라 기본생성자 없으면 안됨
	
	
	// 쿼리스트링에서값을 받아올거면 {} 안에 넣어야함
	@DeleteMapping("/test/{no}")
	public String delete(@PathVariable Integer no) {
		return "delete 요청: " + no;
	}
	
	@PutMapping("/test")
	public String put(@RequestParam Integer page) {
		return "요청한 페이지 번호: "+page;
	} // 요청할 때 /test?page=20 이런식으로URL 작성
	
//	@GetMapping("/user")
//	public User getUser(User user) {
//		System.out.println(user);
//		
//		return user;
//	}
	@GetMapping("/user")
	public User getUser2() {
		// db에서 특정 유저 정보를 가져왔음
		User user = new User(1,"hong","abcd","aaa@gmail.com");
		
		return user;
	}
	
//	
//	@PostMapping("/user")
//	public User postUser(User user) {
//		System.out.println(user);
//		
//		return user;
//	} // 원래 주소방이 나와야되는데 왜 되는지 모르겠음
	
	@PostMapping("/user")
	public void postUser(User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user);
	} // user쪽에서 data 때문에 toString 이 생겨서 주소값 말고 원하는대로 나옴
	
	@PutMapping("/user")
	public String putUser(@RequestParam Integer id) {
		System.out.println("id :" + id);
		return "id 변경완료";
	}
	
}
