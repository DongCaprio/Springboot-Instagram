package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException {
	// Exception이 되기 위해서는 원하는 해당 Exception을 상속하면 된다.

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;

	public CustomValidationException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public CustomValidationException(String message) {
		super(message);
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}
}
