package com.ximple.challenge.ximplelibrarysystem.config.error;

import com.ximple.challenge.ximplelibrarysystem.exceptions.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Hidden
@RestControllerAdvice
public class ExceptionHandlerAPI {

	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetail> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookNotRegisteredException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetail> handleBookNotRegisteredException(BookNotRegisteredException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ReviewsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetail> handleReviewsNotFoundException(ReviewsNotFoundException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetail> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorDetail> handleUserAlreadyException(UserAlreadyException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorDetail> handleUInvalidPasswordException(InvalidPasswordException ex, WebRequest request) {
		var error = new ErrorDetail(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
}
