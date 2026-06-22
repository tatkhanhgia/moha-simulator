package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable value object representing a labor contract UUID.
 */
public final class ContractUuid {

    private final UUID value;

    public ContractUuid(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Contract UUID must not be blank");
        }
        try {
            this.value = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Contract UUID must be a valid UUID: " + value);
        }
    }

    public ContractUuid(UUID value) {
        if (value == null) {
            throw new ValidationException("Contract UUID must not be null");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractUuid that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
