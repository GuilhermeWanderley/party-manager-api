package com.example.party_manager.exception;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String message) {
        super(message);
    }
}