package com.ximple.challenge.ximplelibrarysystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;

	private String title;

	private String author;

	private String genre;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reservation> reservations = new ArrayList<>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();

	public Book() {
	}

	private Book(BookBuilder bookBuilder) {
		this.title = bookBuilder.title;
		this.author = bookBuilder.author;
		this.genre = bookBuilder.genre;
	}

	public long getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

	public List<Reservation> getReservations() {
		return reservations;
	}


	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public static class BookBuilder {
		private String title;
		private String author;
		private String genre;

		public BookBuilder title(String title) {
			this.title = title;
			return this;
		}

		public BookBuilder author(String author) {
			this.author = author;
			return this;
		}

		public BookBuilder genre(String genre) {
			this.genre = genre;
			return this;
		}

		public Book build() {
			return new Book(this);
		}
	}
}
