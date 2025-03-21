package com.ximple.challenge.ximplelibrarysystem.repository;

import com.ximple.challenge.ximplelibrarysystem.model.ApiTokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTokenBlacklistRepository extends JpaRepository<ApiTokenBlacklist, Long> {

	ApiTokenBlacklist findBlackListTokenByToken(String token);
}
