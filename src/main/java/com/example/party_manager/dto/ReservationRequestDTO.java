package com.example.party_manager.dto;

import java.time.LocalDate;

public record ReservationRequestDTO(
        Long clientId,
        LocalDate desiredDate
) {}
