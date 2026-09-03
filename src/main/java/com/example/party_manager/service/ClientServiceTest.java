package com.example.party_manager.service;

import com.example.party_manager.dto.ClientRequestDTO;
import com.example.party_manager.dto.ClientResponseDTO;
import com.example.party_manager.entity.Client;
import com.example.party_manager.entity.UserRole;
import com.example.party_manager.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void deveSalvarClienteComSucesso() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO(
                "Maria Silva",
                "maria@email.com",
                "11987654321",
                UserRole.USER
        );

        Client clienteSalvo = new Client(
                request.name(),
                request.email(),
                request.phoneNumber(),
                request.userRole()
        );
        ReflectionTestUtils.setField(clienteSalvo, "id", 1L);

        when(clientRepository.save(any(Client.class))).thenReturn(clienteSalvo);

        // Act
        ClientResponseDTO response = clientService.save(request);

        // Assert
        assertEquals(1L, response.id());
        assertEquals(request.name(), response.name());
        assertEquals(request.email(), response.email());
        assertEquals(request.phoneNumber(), response.phoneNumber());
        assertEquals(request.userRole(), response.userRole());
    }
}
