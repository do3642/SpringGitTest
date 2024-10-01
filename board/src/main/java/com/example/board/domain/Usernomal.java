package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data  // 게터세터 및 다른  것도 다 가져오는 어노테이션

@NoArgsConstructor
@AllArgsConstructor
public class Usernomal {

	private int id; //회원번호
	private String username; // 회원 아이디
	private String password;// 회원 비번
	private String email; // 회원 이메일
	
}
