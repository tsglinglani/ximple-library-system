package com.ximple.challenge.ximplelibrarysystem.service;

import com.ximple.challenge.ximplelibrarysystem.config.security.encoder.PasswordEncoderBCrypt;
import com.ximple.challenge.ximplelibrarysystem.controller.request.UserRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.UserResponse;
import com.ximple.challenge.ximplelibrarysystem.exceptions.UserAlreadyException;
import com.ximple.challenge.ximplelibrarysystem.exceptions.UserNotFoundException;
import com.ximple.challenge.ximplelibrarysystem.model.User;
import com.ximple.challenge.ximplelibrarysystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final PasswordEncoderBCrypt passwordEncoderBCrypt;

	public UserService(UserRepository userRepository,
	                   PasswordEncoderBCrypt passwordEncoderBCrypt) {

		this.userRepository = userRepository;
		this.passwordEncoderBCrypt = passwordEncoderBCrypt;
	}

	@Transactional
	public UserResponse save(UserRequest userRequest) {
		logger.info("Save user name: {} and e-mail: {}", userRequest.name(), userRequest.email());
		validateIfUserIsRegistered(userRequest);
		return UserResponse.from(userRepository.save(new User.UserBuilder()
				.email(userRequest.email())
				.name(userRequest.name())
				.password(this.passwordEncoder(userRequest.password()))
				.build()));
	}

	public User findByUserId(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(UserNotFoundException::new);
	}

	public UserDetails findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(UserNotFoundException::new);
	}

	private String passwordEncoder(String password) {
		return passwordEncoderBCrypt.passwordEncoder()
				.encode(password);
	}

	private void validateIfUserIsRegistered(UserRequest userRequest) {
		var user = userRepository.findByEmail(userRequest.email());
		if (user.isPresent()) {
			throw new UserAlreadyException(userRequest.email());
		}
	}
}
