package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice //모든 Exception을 다 낚아챈다
public class ControllerExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class) // 이러면 모든 RuntimeException을 이 함수가 가로챈다
	public Map<String, String> validationException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
