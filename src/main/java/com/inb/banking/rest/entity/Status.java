package com.inb.banking.rest.entity;

public class Status {
	private String alreadyExists;
	private int code;
	private String message;
	

	public String getAlreadyExists() {
		return alreadyExists;
	}

	public void setAlreadyExists(String alreadyExists) {
		this.alreadyExists = alreadyExists;
	}

	public Status() {
	}

	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
