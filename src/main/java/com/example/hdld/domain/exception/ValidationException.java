package com.example.hdld.domain.exception;

/**
 * Thrown when input validation fails at the domain level.
 * Maps to HTTP 400.
 */
public class ValidationException extends DomainException {

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "E02";
    }
}
