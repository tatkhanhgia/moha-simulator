package com.example.hdld.domain.exception;

/**
 * Thrown when authentication or authorization fails.
 * Maps to HTTP 401.
 */
public class UnauthorizedException extends DomainException {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "E01";
    }
}
