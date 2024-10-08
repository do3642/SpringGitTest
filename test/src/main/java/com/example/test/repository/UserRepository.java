package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
