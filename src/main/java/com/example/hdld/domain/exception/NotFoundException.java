package com.example.hdld.domain.exception;

/**
 * Thrown when a requested entity is not found.
 * Maps to HTTP 404.
 */
public class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "E04";
    }
}
