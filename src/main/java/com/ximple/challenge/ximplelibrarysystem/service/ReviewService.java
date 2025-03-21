package com.ximple.challenge.ximplelibrarysystem.service;

import com.ximple.challenge.ximplelibrarysystem.controller.request.ReviewRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.ReviewResponse;
import com.ximple.challenge.ximplelibrarysystem.exceptions.ReviewsNotFoundException;
import com.ximple.challenge.ximplelibrarysystem.model.Review;
import com.ximple.challenge.ximplelibrarysystem.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

	private final Logger log = LoggerFactory.getLogger(ReviewService.class);

	private final ReviewRepository reviewRepository;
	private final UserService userService;
	private final BookService bookService;

	public ReviewService(ReviewRepository reviewRepository,
	                     UserService userService,
	                     BookService bookService) {

		this.reviewRepository = reviewRepository;
		this.userService = userService;
		this.bookService = bookService;
	}

	@Transactional
	public ReviewResponse save(ReviewRequest reviewRequest) {
		var user = userService.findByUserId(reviewRequest.UserId());
		var book = bookService.findByBookId(reviewRequest.bookId());

		return ReviewResponse.from(reviewRepository.save(new Review.ReviewBuilder()
				.user(user)
				.book(book)
				.textReview(reviewRequest.textReview())
				.build()));
	}

	public List<ReviewResponse> findByBookId(Long bookId) {
		var book = bookService.findByBookId(bookId);
		return Optional.ofNullable(reviewRepository.findByBook(book))
				.filter(reviews -> !reviews.isEmpty())
				.orElseThrow(ReviewsNotFoundException::new)
				.stream()
				.map(ReviewResponse::from)
				.toList();
	}
}
