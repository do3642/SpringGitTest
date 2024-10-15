package com.example.board.advice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.board.domain.ResponseDTO;

@Component
@Aspect
public class ValidationCheckAdvice {
	
	@Around("execution(* com.example..controller.*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint jp) throws Throwable{
		Object[] args = jp.getArgs();
		//해당 메서드가 실행될 때 매개변수를 리턴 시켜줌
		// getArgs의 리턴방식은 배열임
		
		for(Object arg : args) { //매개변수들 arg에 담음
			if(arg instanceof BindingResult) { // 매개변수 담을때 해당 클래스 가져오기
				// instanceof는 자료형 체크같은거 -> 클래스는 연산자로 비교못하니
				// 클래스를 비교하기 위해 사용함
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String,String> errors = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errors.put(error.getField(), error.getDefaultMessage());
					}
					return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errors);
				}
				
			}
		}
		
		return jp.proceed(); // 해당 매개변수 없으면 정상적으로 다시 리턴하는 역할
	}
}
