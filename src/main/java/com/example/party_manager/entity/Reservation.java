package com.example.party_manager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(name = "uk_reservation_date", columnNames = "reservation_date")
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "reservation_date", nullable = false, unique = true)
    private LocalDate reservationDate;
}
