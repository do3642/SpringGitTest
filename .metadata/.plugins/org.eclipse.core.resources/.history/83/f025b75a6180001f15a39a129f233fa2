package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String Passwrod);
	
	List<User> findByUsernameLike(String keyword);
	
	
}
