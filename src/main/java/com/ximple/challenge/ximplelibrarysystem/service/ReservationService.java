package com.ximple.challenge.ximplelibrarysystem.service;

import com.ximple.challenge.ximplelibrarysystem.controller.request.ReservationRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.ReservationResponse;
import com.ximple.challenge.ximplelibrarysystem.model.Reservation;
import com.ximple.challenge.ximplelibrarysystem.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

	private final ReservationRepository reservationRepository;
	private final UserService userService;
	private final BookService bookService;

	public ReservationService(ReservationRepository reservationRepository,
	                          UserService userService,
	                          BookService bookService) {
		this.reservationRepository = reservationRepository;
		this.userService = userService;
		this.bookService = bookService;
	}

	@Transactional
	public ReservationResponse reservation(ReservationRequest reservationRequest) {
		var user = userService.findByUserId(reservationRequest.userId());
		var book = bookService.findByBookId(reservationRequest.bookId());

		log.info("Reservation fo book: {} and user: {}", book.getTitle(), user.getName());

		return ReservationResponse.from(reservationRepository.save(new Reservation.ReservationBuilder()
				.user(user)
				.book(book)
				.build()));
	}
}
