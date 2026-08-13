package com.example.party_manager.repository;

import com.example.party_manager.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByReservationDate(LocalDate reservationDate);
}
