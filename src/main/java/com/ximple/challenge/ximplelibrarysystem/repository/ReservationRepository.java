package com.ximple.challenge.ximplelibrarysystem.repository;

import com.ximple.challenge.ximplelibrarysystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
