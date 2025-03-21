package com.ximple.challenge.ximplelibrarysystem.exceptions;

public class ReviewsNotFoundException extends RuntimeException {

	private final static String REVIEWS_NOT_FOUND_MESSAGE = "There are no reviews registered for this book.";

	public ReviewsNotFoundException() {
		super(REVIEWS_NOT_FOUND_MESSAGE);
	}
}
