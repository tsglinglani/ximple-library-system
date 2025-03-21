package com.ximple.challenge.ximplelibrarysystem.controller;

import com.ximple.challenge.ximplelibrarysystem.config.doc.ApiInfo;
import com.ximple.challenge.ximplelibrarysystem.controller.request.BookRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.BookResponse;
import com.ximple.challenge.ximplelibrarysystem.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "XIMPLE ONLINE LIBRARY SYSTEM - Books", description = "Domain: Books")
@RestController
@RequestMapping(ApiInfo.API_V1)
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/books")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Save book", description = "Create a book in DB")
	public ResponseEntity<BookResponse> save(@RequestBody BookRequest bookRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookService.save(bookRequest));
	}

	@GetMapping("/books")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Find books", description = "List or search book(find)")
	public ResponseEntity<List<BookResponse>> find(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String genre) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(bookService.findBook(title, author, genre));
	}
}
