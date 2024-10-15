package com.example.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.board.domain.User;
import com.example.board.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	//사용자가 입력한 username이 들어옴
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException(username + "은 없는 아이디입니다.");
		}); //db에 있으면 객체에 담기고 없으면 리턴값 반환됨
		
		//객체에 db레코드가 담기면 시큐리티에 리턴시킴
		return new UserDetailsImpl(principal);
	}

}
