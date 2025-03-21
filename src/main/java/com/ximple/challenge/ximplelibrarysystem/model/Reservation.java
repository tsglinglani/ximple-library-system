package com.ximple.challenge.ximplelibrarysystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private LocalDateTime reservationDate;

	public Reservation() {
	}

	private Reservation(ReservationBuilder reservationBuilder) {
		this.book = reservationBuilder.book;
		this.user = reservationBuilder.user;
		this.reservationDate = LocalDateTime.now();
	}

	public Long getReservationId() {
		return reservationId;
	}

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public static class ReservationBuilder {
		private Book book;
		private User user;

		public ReservationBuilder book(Book book) {
			this.book = book;
			return this;
		}

		public ReservationBuilder user(User user) {
			this.user = user;
			return this;
		}

		public Reservation build() {
			return new Reservation(this);
		}
	}
}
