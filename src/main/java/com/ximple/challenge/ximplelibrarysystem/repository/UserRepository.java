package com.ximple.challenge.ximplelibrarysystem.repository;

import com.ximple.challenge.ximplelibrarysystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<UserDetails> findByEmail(String email);
}
