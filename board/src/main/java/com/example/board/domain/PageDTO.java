package com.example.board.domain;

import org.springframework.data.domain.Page;

import lombok.Getter;


@Getter
public class PageDTO {
	
	//페이지 블록에서 시작번호와 끝번호를 저장하는 변수들
	
	private int startPage;
	private int endPage;
	private boolean prev, next; //이전, 다음 블록 존재 여부를 저장
	
	// 페이지 데이터를 저장하고 있는 변수
	private Page<Post> page;
	private int totalPages; // 위 page객체가 전체를 저장하고 있어 원래는 필요없음 // 전체 페이지 수
	private long totalElements; // 위와 동일 // 전체 레코드 수
	
	
	public PageDTO(Page<Post> page) {
		// 원래는 재사용성을 위해 제네릭으로 받는게 맞음
		this.page = page;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		
		// 현재 페이지를 리턴시키는 메서드
		int currentPage = page.getNumber() + 1;
		
		// 한 블럭에 3개씩 페이지 번호가 보이게 하고 싶음
		// 만약 내가 1,2,3 페이지 보고 있으면 마지막 페이지는 3
		// 만약 내가 4,5,6 페이지를 보고 있으면 마지막 페이지는 6
		// (현재 페이지 /블럭당 페이지 수)* 블럭당 페이지 수
		this.endPage = (int) (Math.ceil(currentPage / 3.0) * 3);
		this.startPage = endPage - 2;
		
		if(totalPages < endPage) {
			endPage = totalPages;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.totalPages;
		
		
		
	}
	
}
