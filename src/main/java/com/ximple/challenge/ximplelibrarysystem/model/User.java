package com.ximple.challenge.ximplelibrarysystem.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String name;

	private String email;

	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reservation> reservations = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();

	public User() {
	}

	private User(UserBuilder userBuilder) {
		this.userId = userBuilder.userId;
		this.name = userBuilder.name;
		this.email = userBuilder.email;
		this.password = userBuilder.password;
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static class UserBuilder {
		private Long userId;
		private String name;
		private String email;
		public String password;

		public UserBuilder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public UserBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
