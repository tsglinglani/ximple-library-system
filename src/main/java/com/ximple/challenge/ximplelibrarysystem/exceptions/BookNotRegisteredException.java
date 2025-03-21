package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class BookNotRegisteredException extends RuntimeException {

	private final static String BOOKS_NOT_REGISTERED_MESSAGE = "Books not registered.";

	public BookNotRegisteredException() {
		super(BOOKS_NOT_REGISTERED_MESSAGE);
	}
}
