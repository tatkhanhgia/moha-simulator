package com.example.hdld.domain.exception;

/**
 * Base exception for all domain-level errors.
 * Maps to HTTP 500.
 */
public class DomainException extends RuntimeException {

    private final String errorCode;

    public DomainException(String message) {
        super(message);
        this.errorCode = "E06";
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "E06";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
