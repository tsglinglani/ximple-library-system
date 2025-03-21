package com.ximple.challenge.ximplelibrarysystem.controller.response;

import com.ximple.challenge.ximplelibrarysystem.model.Reservation;

import java.time.LocalDateTime;

public record ReservationResponse(Long reservationId,
                                  Long userId,
                                  String name,
                                  Long bookId,
                                  String title,
                                  LocalDateTime localDateTime) {

	public static ReservationResponse from(Reservation reservation) {
		return new ReservationResponse(reservation.getReservationId(),
				reservation.getUser().getUserId(),
				reservation.getUser().getName(),
				reservation.getBook().getBookId(),
				reservation.getBook().getTitle(),
				reservation.getReservationDate());
	}
}
