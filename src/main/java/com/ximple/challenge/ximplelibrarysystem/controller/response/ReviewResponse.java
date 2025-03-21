package com.ximple.challenge.ximplelibrarysystem.controller.response;

import com.ximple.challenge.ximplelibrarysystem.model.Review;

import java.time.LocalDateTime;

public record ReviewResponse(Long reviewId,
                             Long UserId,
                             String name,
                             Long bookId,
                             String title,
                             String textReview,
                             LocalDateTime reviewDate) {

	public static ReviewResponse from(Review review) {
		return new ReviewResponse(review.getReviewId(),
				review.getUser().getUserId(),
				review.getUser().getName(),
				review.getBook().getBookId(),
				review.getBook().getTitle(),
				review.getTextReview(),
				review.getReviewDate());
	}
}
