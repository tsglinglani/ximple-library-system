package com.ximple.challenge.ximplelibrarysystem.controller;


import com.ximple.challenge.ximplelibrarysystem.config.doc.ApiInfo;
import com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig.ApiTokenBlacklistService;
import com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig.ApiTokenService;
import com.ximple.challenge.ximplelibrarysystem.controller.request.AuthenticationRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.DataTokenJWTResponse;
import com.ximple.challenge.ximplelibrarysystem.exceptions.InvalidPasswordException;
import com.ximple.challenge.ximplelibrarysystem.model.User;
import com.ximple.challenge.ximplelibrarysystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "XIMPLE ONLINE LIBRARY SYSTEM - Authentication", description = "Domain: Authentication")
@RestController
@RequestMapping(ApiInfo.API_V1)
public class AuthenticationController {

	private final ApiTokenService apiTokenService;
	private final ApiTokenBlacklistService apiTokenBlacklistService;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationController(ApiTokenService apiTokenService,
	                                ApiTokenBlacklistService apiTokenBlacklistService,
	                                UserService userService, PasswordEncoder passwordEncoder) {

		this.apiTokenService = apiTokenService;
		this.apiTokenBlacklistService = apiTokenBlacklistService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	@Operation(summary = "Login user", description = "Login user in system")
	public ResponseEntity<DataTokenJWTResponse> efetuarLogin(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
		var user = userService.findByEmail(authenticationRequest.email());

		if (!passwordEncoder.matches(authenticationRequest.senha(), user.getPassword())) {
			throw new InvalidPasswordException();
		}

		var tokenJWT = apiTokenService.gerarToken((User) user);

		return ResponseEntity.ok(new DataTokenJWTResponse(tokenJWT, ((User) user).getUserId()));
	}

	@PostMapping("/logout")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Logout user", description = "Logout user of system")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		var token = apiTokenService.extractTokenFromRequest(request);
		apiTokenBlacklistService.salvarTokenBlackList(token);

		return ResponseEntity.ok("Logout completed successfully.");
	}
}
