package com.example.party_manager.service;

import com.example.party_manager.dto.ClientRequestDTO;
import com.example.party_manager.dto.ClientResponseDTO;
import com.example.party_manager.entity.Client;
import com.example.party_manager.exception.DuplicateClientException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.party_manager.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientResponseDTO save(ClientRequestDTO request) {
        Client client = new Client(
                request.name(),
                request.email(),
                request.phoneNumber(),
                request.userRole()
        );

        try {
            Client saved = repository.save(client);
            return ClientResponseDTO.fromEntity(saved);
        } catch (DataIntegrityViolationException e) {
            String constraintName = extractConstraintName(e);

            if ("uk_client_email".equals(constraintName)) {
                throw new DuplicateClientException("Client with this email already exists");
            }
            if ("uk_client_phone_number".equals(constraintName)) {
                throw new DuplicateClientException("Client with this phone number already exists");
            }
            throw e;
        }
    }

    public List<ClientResponseDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(ClientResponseDTO::fromEntity)
                .toList();
    }

    private String extractConstraintName(DataIntegrityViolationException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConstraintViolationException cve) {
            return cve.getConstraintName();
        }
        return null;
    }
}