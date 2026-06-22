package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;

import java.util.Objects;

/**
 * Immutable value object representing a Vietnamese tax code (ma so thue).
 * Must be exactly 10 or 13 digits.
 */
public final class TaxCode {

    private static final String PATTERN = "^\\d{10}(\\d{3})?$";

    private final String value;

    public TaxCode(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Tax code must not be blank");
        }
        if (!value.matches(PATTERN)) {
            throw new ValidationException("Tax code must be 10 or 13 digits: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxCode taxCode)) return false;
        return Objects.equals(value, taxCode.value);
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
