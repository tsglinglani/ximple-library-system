package com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ximple.challenge.ximplelibrarysystem.model.User;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Hidden
@Service
public class ApiTokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(User user) {
		try {

			var expirationDate = LocalDateTime.now()
					.plusMinutes(40L)
					.toInstant(ZoneOffset.of("-03:00"));

			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("challenge ximple")
					.withSubject(user.getEmail())
					.withExpiresAt(expirationDate)
					.sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error generating token.", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo)
					.withIssuer("challenge ximple")
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Invalid or expired JWT token");
		}
	}

	public String extractTokenFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}

}
