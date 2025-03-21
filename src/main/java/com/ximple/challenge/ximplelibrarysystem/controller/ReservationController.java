package com.ximple.challenge.ximplelibrarysystem.controller;

import com.ximple.challenge.ximplelibrarysystem.config.doc.ApiInfo;
import com.ximple.challenge.ximplelibrarysystem.controller.request.ReservationRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.ReservationResponse;
import com.ximple.challenge.ximplelibrarysystem.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "XIMPLE ONLINE LIBRARY SYSTEM - Reservations", description = "Domain: Reservations")
@RestController
@RequestMapping(ApiInfo.API_V1)
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping("/reservations")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Reservations book", description = "Create a reservations book in DB")
	public ResponseEntity<ReservationResponse> reservations(@RequestBody ReservationRequest reservationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(reservationService.reservation(reservationRequest));
	}
}
