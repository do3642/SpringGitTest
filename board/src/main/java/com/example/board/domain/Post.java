package com.example.board.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //varchar은 길이제한이 있어 대용량 내용을 넣을 수 있는 데이터타입 선언
	@Column(nullable = false)
	private String content;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	private int cnt;
	
	// 엔티티는 user의 id만 가져오기 번거로우니
	// 객체 형태로 가져온다, 관계는 설정해야함 1:N상태이니 ManyToOne
	@ManyToOne(fetch = FetchType.EAGER)
	// EAGER ->게시글 정보 로드 될때 동시에 유저정보를 가져옴, LAZY->사용할 때 가져옴
	
	@JoinColumn(name = "userid") // 외래키 설정
	private User user;
	
	
	
	

}
