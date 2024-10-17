package com.example.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;

@Service
public class OAuth2UserDetailsServiceImpl extends DefaultOAuth2UserService {

	@Value("${google.default.password")
	private String googlePassword;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		
		//엑세스토큰이 포함된 userRequest객체를 이용해서
		//loadUser메서드를 호출하면 구글에서 사용자 정보를 받아옴
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		//DB에 저장하기 위해 구글 사용자 정보를 세팅함 
		String id = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String username = email + "_" + id;
		String password = googlePassword;
		
		//DB에 이미 있는지 없는지 체크
		User findUser = userRepository.findByUsername(username).orElseGet(() ->{
			return new User();
		});
		if(findUser.getUsername() == null) {
			findUser.setUsername(username);
			findUser.setPassword(passwordEncoder.encode(password));
			findUser.setEmail(email);
			findUser.setRole(RoleType.USER);
			
			userRepository.save(findUser);
			
		}
		// 해당 클래스에 매개변수 2개인 생성자 구현해놨음
		return new UserDetailsImpl(findUser,oAuth2User.getAttributes());
		
	}

}
