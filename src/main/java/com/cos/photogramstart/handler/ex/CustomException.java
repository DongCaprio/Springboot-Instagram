package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {
	// Exception이 되기 위해서는 원하는 해당 Exception을 상속하면 된다.

	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}
}
