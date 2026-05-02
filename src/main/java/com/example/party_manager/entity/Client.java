package com.example.party_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private UserRole userRole;

    public Client(String name, String email, String phoneNumber, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }
}
