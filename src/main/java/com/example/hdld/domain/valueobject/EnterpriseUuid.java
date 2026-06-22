package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable value object representing an enterprise UUID.
 */
public final class EnterpriseUuid {

    private final UUID value;

    public EnterpriseUuid(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Enterprise UUID must not be blank");
        }
        try {
            this.value = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Enterprise UUID must be a valid UUID: " + value);
        }
    }

    public EnterpriseUuid(UUID value) {
        if (value == null) {
            throw new ValidationException("Enterprise UUID must not be null");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterpriseUuid that)) return false;
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
