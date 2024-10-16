package com.example.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;
import com.example.board.domain.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	//제네릭 첫번째 테이블명, 두번째 기본키의 자료형, 제네릭이니깐 int=>Integer
	
	
	
	//select * from post where title like “%검색어%”
	//페이징 처리 안된거
	List<Post> findByTitleContaining(String keyword);
	
	Page<Post> findByTitleContaining(String keyword, Pageable pageable);

}
