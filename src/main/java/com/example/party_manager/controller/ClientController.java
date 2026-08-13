package com.example.party_manager.controller;

import com.example.party_manager.dto.ClientRequestDTO;
import com.example.party_manager.dto.ClientResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.party_manager.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> register(@Valid @RequestBody ClientRequestDTO request) {
        ClientResponseDTO response = clientService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> list() {
        return ResponseEntity.ok(clientService.listAll());
    }
}

