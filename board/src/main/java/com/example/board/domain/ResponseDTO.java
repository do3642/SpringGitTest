package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 클래스에 제네릭을 넣음 T는 Type의 약자
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
	
	// 응갑결과 상태코드
	private int status;
	// 응답 데이터
	private T data;
}
