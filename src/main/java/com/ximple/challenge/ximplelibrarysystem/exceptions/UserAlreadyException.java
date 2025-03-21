package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class UserAlreadyException extends RuntimeException{

	private final static String USER_ALREADY_EXISTS = "User with email: %s is already registered";

	public UserAlreadyException(String email) {
		super(String.format(USER_ALREADY_EXISTS, email));
	}
}
