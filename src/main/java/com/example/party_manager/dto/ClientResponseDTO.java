package com.example.party_manager.dto;

import com.example.party_manager.entity.Client;
import com.example.party_manager.entity.UserRole;

public record ClientResponseDTO(
        long id,
        String name,
        String email,
        String phoneNumber,
        UserRole userRole
) {
    public static ClientResponseDTO fromEntity(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getUserRole()
        );
    }
}

