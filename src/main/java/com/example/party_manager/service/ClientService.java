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

    public Client save(Client client){
       if(repository.findByPhoneNumber(client.getPhoneNumber()).isPresent()) {
           throw new RuntimeException("Error: This phone is already in use!");
       }
       if (repository.findByEmail(client.getEmail()).isPresent()) {
           throw new RuntimeException("Error: This email is already in use!");
       }

        return repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

}
