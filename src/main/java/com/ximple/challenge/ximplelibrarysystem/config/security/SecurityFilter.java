package com.ximple.challenge.ximplelibrarysystem.config.security;

import com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig.ApiTokenBlacklistService;
import com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig.ApiTokenService;
import com.ximple.challenge.ximplelibrarysystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final ApiTokenService apiTokenService;
	private final UserService userService;
	private final ApiTokenBlacklistService apiTokenBlacklistService;

	public SecurityFilter(ApiTokenService apiTokenService,
	                      UserService userService,
	                      ApiTokenBlacklistService apiTokenBlacklistService) {

		this.apiTokenService = apiTokenService;
		this.userService = userService;
		this.apiTokenBlacklistService = apiTokenBlacklistService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		var tokenJWT = recuperarTokenJWT(request);
		var tokenBlacklisted = apiTokenBlacklistService.getTokenBlacklisted(tokenJWT);

		if (tokenJWT != null && tokenBlacklisted == null) {
			var subject = apiTokenService.getSubject(tokenJWT);
			var userDetails = userService.findByEmail(subject);
			var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recuperarTokenJWT(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}
}
