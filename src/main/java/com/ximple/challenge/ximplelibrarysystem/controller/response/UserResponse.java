package com.ximple.challenge.ximplelibrarysystem.controller.response;

import com.ximple.challenge.ximplelibrarysystem.model.User;

public record UserResponse(Long userId,
                           String name,
                           String email) {

	public static UserResponse from(User user) {
		return new UserResponse(user.getUserId(), user.getName(), user.getEmail());
	}
}
