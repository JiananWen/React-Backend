package com.korera.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;

	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}