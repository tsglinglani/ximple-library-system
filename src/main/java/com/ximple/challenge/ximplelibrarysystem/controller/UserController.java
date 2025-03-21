package com.ximple.challenge.ximplelibrarysystem.controller;

import com.ximple.challenge.ximplelibrarysystem.config.doc.ApiInfo;
import com.ximple.challenge.ximplelibrarysystem.controller.request.UserRequest;
import com.ximple.challenge.ximplelibrarysystem.controller.response.UserResponse;
import com.ximple.challenge.ximplelibrarysystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "XIMPLE ONLINE LIBRARY SYSTEM - Users", description = "Domain: Users")
@RestController
@RequestMapping(ApiInfo.API_V1)
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/users")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(summary = "Save user", description = "Create a user in DB")
	public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(userRequest));
	}
}
