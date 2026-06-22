package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;

import java.util.Objects;

/**
 * Immutable value object representing an email address.
 * Simplified RFC 5322 regex, max 254 characters.
 */
public final class Email {

    private static final String PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final int MAX_LENGTH = 254;

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Email must not be blank");
        }
        if (value.length() > MAX_LENGTH) {
            throw new ValidationException("Email must not exceed " + MAX_LENGTH + " characters");
        }
        if (!value.matches(PATTERN)) {
            throw new ValidationException("Email format is invalid: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return Objects.equals(value, email.value);
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
