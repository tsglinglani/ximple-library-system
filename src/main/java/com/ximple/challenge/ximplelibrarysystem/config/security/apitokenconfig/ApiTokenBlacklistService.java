package com.ximple.challenge.ximplelibrarysystem.config.security.apitokenconfig;


import com.ximple.challenge.ximplelibrarysystem.model.ApiTokenBlacklist;
import com.ximple.challenge.ximplelibrarysystem.repository.ApiTokenBlacklistRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Hidden
@Service
public class ApiTokenBlacklistService {

	private final ApiTokenBlacklistRepository repository;

	public ApiTokenBlacklistService(ApiTokenBlacklistRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void salvarTokenBlackList(String token) {
		var tokenDelete = new ApiTokenBlacklist.ApiBlackListTokenBuilder()
				.token(token)
				.build();

		repository.save(tokenDelete);
	}

	public ApiTokenBlacklist getTokenBlacklisted(String token) {
		return repository.findBlackListTokenByToken(token);
	}


}
