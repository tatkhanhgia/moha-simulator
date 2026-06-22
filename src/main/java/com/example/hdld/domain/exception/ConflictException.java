package com.example.hdld.domain.exception;

/**
 * Thrown when a business conflict occurs (e.g., duplicate unique key).
 * Maps to HTTP 409.
 */
public class ConflictException extends DomainException {

    public ConflictException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "E05";
    }
}
