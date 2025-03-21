package com.ximple.challenge.ximplelibrarysystem.config.security.encoder;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Hidden
@Configuration
public class PasswordEncoderBCrypt {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
