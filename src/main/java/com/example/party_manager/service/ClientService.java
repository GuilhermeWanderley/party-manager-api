package com.example.party_manager.service;

import com.example.party_manager.entity.Client;
import com.example.party_manager.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.party_manager.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client newClient() {
        Client client1 = new Client("Guilherme", "pireswanderçey12@gmail.com", "75983739836", UserRole.ADMIN);
        return repository.save(client1);
    }

    public Client save(Client client) {
        repository.findByEmail(client.getEmail()).ifPresent(existingClient -> {
            throw new IllegalArgumentException("Email already exists: " + client.getEmail());
        });

        repository.findByPhoneNumber(client.getPhoneNumber()).ifPresent(existingClient -> {
            throw new IllegalArgumentException("Phone number already exists: " + client.getPhoneNumber());
        });

        return repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }
}
