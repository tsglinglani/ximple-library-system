package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class BookNotFoundException extends RuntimeException {

	private final static String BOOK_NOT_FOUND_MESSAGE = "Book not found.";

	public BookNotFoundException() {
		super(BOOK_NOT_FOUND_MESSAGE);
	}
}
