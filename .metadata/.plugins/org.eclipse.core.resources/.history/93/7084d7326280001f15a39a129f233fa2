package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional //org.springframework로 import 해야됨, 무언가 잘못되면 save가 되지 않도록 하는 기능
	public void insertUser(User user) {
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
	}
}
