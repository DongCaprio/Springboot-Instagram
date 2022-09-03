package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;

@RestController
@ControllerAdvice // 모든 Exception을 다 낚아챈다
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // 이러면 모든 RuntimeException을 이 함수가 가로챈다
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
}
