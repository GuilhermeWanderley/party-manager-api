package com.example.party_manager.controller;

import com.example.party_manager.entity.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.party_manager.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/test")
    public Client send() {
        return clientService.newClient();
    }

    @PostMapping("/postman")
    public Client register(@Valid @RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping
    public List<Client> list() {
        return clientService.listAll();
    }
}
