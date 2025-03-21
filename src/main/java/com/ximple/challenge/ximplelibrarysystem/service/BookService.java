package com.ximple.challenge.ximplelibrarysystem.service;

import com.ximple.challenge.ximplelibrarysystem.controller.request.BookRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.BookResponse;
import com.ximple.challenge.ximplelibrarysystem.exceptions.BookNotFoundException;
import com.ximple.challenge.ximplelibrarysystem.exceptions.BookNotRegisteredException;
import com.ximple.challenge.ximplelibrarysystem.model.Book;
import com.ximple.challenge.ximplelibrarysystem.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional
	public BookResponse save(BookRequest bookRequest) {
		return BookResponse.from(this.bookRepository.save(new Book.BookBuilder()
				.author(bookRequest.author())
				.title(bookRequest.title())
				.genre(bookRequest.genre())
				.build()));
	}

	public List<BookResponse> findBook(String title, String author, String genre) {
		var isParameterNotNull = Stream.of(title, author, genre).anyMatch(Objects::nonNull);
		loggerFindBook(title, author, genre, isParameterNotNull);

		return Optional.ofNullable(isParameterNotNull ? this.bookRepository.findBookByAuthorOrTitleOrGenre(author, title, genre) : this.bookRepository.findAll())
				.filter(books -> !books.isEmpty())
				.orElseThrow(() -> isParameterNotNull ? new BookNotFoundException() : new BookNotRegisteredException())
				.stream()
				.map(BookResponse::from)
				.toList();
	}

	public Book findByBookId(Long bookId) {
		return bookRepository.findById(bookId)
				.orElseThrow(BookNotFoundException::new);
	}

	private void loggerFindBook(String title, String author, String genre, boolean isParameterNotNull) {
		if (isParameterNotNull) {
			logger.info("Find book by title: {} and author: {} and genre: {}", title, author, genre);
		} else {
			logger.info("Find all books");
		}
	}
}
