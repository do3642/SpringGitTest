package com.example.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	
	@NotNull(message = "제목은 null이면 안됩니다.")
	@NotBlank(message = "제목은 반드시 입력하셔야 합니다.")
	private String title;
	
	@NotNull(message = "내용은 null이면 안됩니다.")
	@NotBlank(message = "내용은 반드시 입력하셔야 합니다.")
	private String content;
	
}
