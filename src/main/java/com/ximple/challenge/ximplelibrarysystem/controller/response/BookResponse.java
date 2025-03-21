package com.ximple.challenge.ximplelibrarysystem.controller.response;

import com.ximple.challenge.ximplelibrarysystem.model.Book;

public record BookResponse(Long bookId,
                           String title,
                           String author,
                           String genre) {

	public static BookResponse from(Book book) {
		return new BookResponse(book.getBookId(),
				book.getTitle(),
				book.getAuthor(),
				book.getGenre());
	}
}
