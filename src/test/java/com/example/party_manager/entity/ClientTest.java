package com.example.party_manager.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Test
    void testClientCreation() {
        String name = "Maria Silva";
        String email = "maria@email.com";
        String phoneNumber = "11987654321";
        UserRole role = UserRole.USER;

        Client client = new Client(name, email, phoneNumber, role);

        assertEquals(name, client.getName());
        assertEquals(email, client.getEmail());
        assertEquals(phoneNumber, client.getPhoneNumber());
        assertEquals(role, client.getUserRole());
    }

}

