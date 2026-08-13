package com.example.party_manager.service;

import com.example.party_manager.dto.ReservationRequestDTO;
import com.example.party_manager.dto.ReservationResponseDTO;
import com.example.party_manager.entity.Client;
import com.example.party_manager.entity.Reservation;
import com.example.party_manager.exception.InvalidReservationException;
import com.example.party_manager.exception.ReservationConflictException;
import com.example.party_manager.exception.ResourceNotFoundException;
import com.example.party_manager.repository.ClientRepository;
import com.example.party_manager.repository.ReservationRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }


    public ReservationResponseDTO allocateSpace(ReservationRequestDTO request) {
        LocalDate desiredDate = request.desiredDate();
        Long clientId = request.clientId();

        if (desiredDate.isBefore(LocalDate.now())) {
            throw new InvalidReservationException("Cannot reserve a date in the past: " + desiredDate);
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setReservationDate(desiredDate);

        try {
            Reservation saved = reservationRepository.save(reservation);
            return ReservationResponseDTO.fromEntity(saved);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationConflictException("Date already reserved: " + desiredDate);
        }
    }

    public List<ReservationResponseDTO> listAll() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationResponseDTO::fromEntity)
                .toList();
    }

}
