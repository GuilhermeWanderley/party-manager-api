package com.example.party_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_client_phone_number", columnNames = "phone_number")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{11}", message = "Phone number must be 11 digits")
    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "User role is mandatory")
    @Column(nullable = false)
    private UserRole userRole;

    public Client(String name, String email, String phoneNumber, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }
}
