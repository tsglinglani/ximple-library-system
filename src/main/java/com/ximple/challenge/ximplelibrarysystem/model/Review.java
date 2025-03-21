package com.ximple.challenge.ximplelibrarysystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String textReview;

	private LocalDateTime reviewDate;

	public Review() {
	}

	private Review(ReviewBuilder reviewBuilder) {
		this.book = reviewBuilder.book;
		this.user = reviewBuilder.user;
		this.textReview = reviewBuilder.textReview;
		this.reviewDate = LocalDateTime.now();
	}

	public Long getReviewId() {
		return reviewId;
	}

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}

	public String getTextReview() {
		return textReview;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public static class ReviewBuilder {
		private Book book;
		private User user;
		private String textReview;

		public ReviewBuilder book(Book book) {
			this.book = book;
			return this;
		}

		public ReviewBuilder user(User user) {
			this.user = user;
			return this;
		}

		public ReviewBuilder textReview(String textReview) {
			this.textReview = textReview;
			return this;
		}

		public Review build() {
			return new Review(this);
		}
	}
}
