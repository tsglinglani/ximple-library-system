package com.ximple.challenge.ximplelibrarysystem.controller;

import com.ximple.challenge.ximplelibrarysystem.config.doc.ApiInfo;
import com.ximple.challenge.ximplelibrarysystem.controller.request.ReviewRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.ReviewResponse;
import com.ximple.challenge.ximplelibrarysystem.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "XIMPLE ONLINE LIBRARY SYSTEM - Reviews", description = "Domain: Reviews")
@RestController
@RequestMapping(ApiInfo.API_V1)
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/reviews")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Reviews book", description = "Create a reviews book in DB")
	public ResponseEntity<ReviewResponse> save(@RequestBody ReviewRequest reviewRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(reviewService.save(reviewRequest));
	}

	@GetMapping("/reviews")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Find reviews", description = "Find reviews by book")
	public ResponseEntity<List<ReviewResponse>> find(@RequestParam Long bookId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(reviewService.findByBookId(bookId));
	}
}
