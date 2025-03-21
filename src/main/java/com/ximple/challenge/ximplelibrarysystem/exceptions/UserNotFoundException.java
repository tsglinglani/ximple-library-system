package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class UserNotFoundException extends RuntimeException {

	private final static String USER_NOT_FOUND_MESSAGE = "User not found";

	public UserNotFoundException() {
		super(USER_NOT_FOUND_MESSAGE);
	}
}
