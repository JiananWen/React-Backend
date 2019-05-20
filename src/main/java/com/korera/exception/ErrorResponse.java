package com.korera.exception;

public class ErrorResponse {

	private int errorCode;
	private String message;

	public ErrorResponse() {

	}

	public ErrorResponse(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int i) {
		this.errorCode = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
