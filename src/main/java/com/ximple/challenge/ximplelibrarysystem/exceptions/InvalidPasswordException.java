package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class InvalidPasswordException extends RuntimeException {
	private final static String INVALID_PASSWORD = "Invalid password";

	public InvalidPasswordException() {
		super(INVALID_PASSWORD);
	}
}
