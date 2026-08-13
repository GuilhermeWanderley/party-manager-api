package com.example.party_manager.dto;

import com.example.party_manager.entity.Reservation;

import java.time.LocalDate;

public record ReservationResponseDTO(
        long id,
        LocalDate desiredDate,
        long clientId,
        String clientName

) {
    public static ReservationResponseDTO fromEntity(Reservation reservation) {
        return new ReservationResponseDTO(
          reservation.getId(),
          reservation.getReservationDate(),
          reservation.getClient().getId(),
          reservation.getClient().getName()
        );
    }
}
