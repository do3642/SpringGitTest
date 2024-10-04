package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;

@Service
public class PostService {

	
	@Autowired
	private PostRepository postRepository;
	
//	@Transactional //org.springframework로 import 해야됨, 무언가 잘못되면 save가 되지 않도록 하는 기능
//	public void insertPost(Post post) {
//		
//		postRepository.save(post);
//	}
	
	public void insertPost(Post post, User user) {
		
		// 추출한 유저 정보를 post객체에 넣어줘야함
		post.setUser(user);
		// cnt도 0으로 설정
		post.setCnt(0); // 굳이 안해도 되긴함
		// 설정이 끝난 post객체를 db에 저장
		postRepository.save(post);
	}
	
}
