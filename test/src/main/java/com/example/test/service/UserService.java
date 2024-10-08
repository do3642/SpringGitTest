package com.example.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.domain.User;
import com.example.test.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	public int idCheck(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			//중복아님
			return 0;
		}else {
			//중복
			return 1;
		}
	}
	
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
	
}
