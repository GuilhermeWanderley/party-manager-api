package com.example.party_manager.exception;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException (String message) {
        super(message);
    }
}
