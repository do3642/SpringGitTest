package com.example.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.repository.UserRepository;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
//		User user = new User();
//		user.setUsername("kim");
//		user.setPassword("1234");
//		user.setEmail("asd@naver.com");
//		user.setRole(RoleType.USER);
//			
//		// insert 해주는 메서드
//		userRepository.save(user);
		
//		List<User> users = userRepository.findAll();
//		System.out.println(users.size());
//		
//		for(User u : users) {
//			System.out.println(u);
//		}
		
//		기본키를 이용해 조건을 거는거 까지만 구현됨
		// select * from users where id=?;
//		Optional<User> result = userRepository.findById(1);
		// findById에서 Id는 기본키를 가르키는거
		// 검색결과가 하나이며 없을수도 있어 user 객체가 아닌 옵셔너리 형태로 리턴됨
		
//		System.out.println(result.isPresent());
//		// 있냐 없냐의 true false로 리턴됨
//		
//		if(result.isPresent()) {
//			User u = result.get();
//			System.out.println(u);
//		}else {
//			System.out.println("검색 결과 없음");
//		}
//		
		// username을 기준으로 검색한 결과
		// 해당 레파스토리에 추상메서드로 만들어 놔야함
//		User result = userRepository.findByUsername("hong");
//		System.out.println(result);
		
		//username과 password를 기준으로 검색
//		User result = userRepository.findByUsernameAndPassword("hong", "12");
//		System.out.println(result);
		
//		// 검색어
//		String keyword = "on";
//		
//		// 해당 검색어가 포함하는 레코드를 조회
//		// 포함하는 이라는 걸로 처리되도록 만능 문자가 있어야 함
//		keyword = "%" + keyword + "%";
//		// 사용자가 %를 입력하진 않으니 내부적으로 붙여줘야함
//		List<User> result = userRepository.findByUsernameLike(keyword);
//		for(User u : result) {
//			System.out.println(u);
//		}
		
//		// id가 1번인 사람의 이름을 park로 수정
//		Optional<User> user = userRepository.findById(1);
//		User u =  user.get();
//		u.setUsername("park");
//		userRepository.save(u);
		
		
//		 id가 1번인 레코드를 삭제
		userRepository.deleteById(1);
		
	}

}
