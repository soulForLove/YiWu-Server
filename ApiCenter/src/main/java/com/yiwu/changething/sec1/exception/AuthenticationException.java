package com.yiwu.changething.sec1.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message, Exception cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}
