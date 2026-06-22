package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;

import java.util.Objects;

/**
 * Immutable value object representing a transaction identifier.
 * Alphanumeric, length 1-32.
 */
public final class TransactionId {

    private static final String PATTERN = "^[a-zA-Z0-9]+$";
    private static final int MAX_LENGTH = 32;

    private final String value;

    public TransactionId(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Transaction ID must not be blank");
        }
        if (value.length() > MAX_LENGTH) {
            throw new ValidationException("Transaction ID must not exceed " + MAX_LENGTH + " characters");
        }
        if (!value.matches(PATTERN)) {
            throw new ValidationException("Transaction ID must be alphanumeric: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionId that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
