package com.example.party_manager.dto;

import com.example.party_manager.entity.UserRole;
import jakarta.validation.constraints.*;

public record ClientRequestDTO(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "\\d{11}", message = "Phone number must be 11 digits")
        String phoneNumber,

        @NotNull(message = "User role is mandatory")
        UserRole userRole
) {}

