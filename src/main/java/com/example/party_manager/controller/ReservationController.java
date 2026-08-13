package com.example.party_manager.controller;

import com.example.party_manager.dto.ReservationRequestDTO;
import com.example.party_manager.dto.ReservationResponseDTO;
import com.example.party_manager.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> allocateSpace(@Valid @RequestBody ReservationRequestDTO request) {
        ReservationResponseDTO response = reservationService.allocateSpace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> listAll() {
        List<ReservationResponseDTO> reservation = reservationService.listAll();
        return ResponseEntity.ok(reservation);
    }
}
