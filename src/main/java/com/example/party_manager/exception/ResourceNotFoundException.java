package com.example.party_manager.exception;

public class ResourceNotFoundException  extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
