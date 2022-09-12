package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice // 모든 Exception을 다 낚아챈다
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // 이러면 모든 RuntimeException을 이 함수가 가로챈다
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}

	@ExceptionHandler(CustomValidationApiException.class) // 이러면 모든 RuntimeException을 이 함수가 가로챈다
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}
