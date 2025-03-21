package com.ximple.challenge.ximplelibrarysystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ApiTokenBlacklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blackListId;

	private String token;

	public ApiTokenBlacklist() {
	}

	public long getBlackListId() {
		return blackListId;
	}

	public String getToken() {
		return token;
	}

	private ApiTokenBlacklist(ApiBlackListTokenBuilder apiBlackListTokenBuilder) {
		this.token = apiBlackListTokenBuilder.token;
	}

	public static class ApiBlackListTokenBuilder {
		private String token;

		public ApiBlackListTokenBuilder token(String token) {
			this.token = token;
			return this;
		}

		public ApiTokenBlacklist build() {
			return new ApiTokenBlacklist(this);
		}
	}
}
