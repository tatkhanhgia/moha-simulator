package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCodeTest {

    @Test
    void shouldAccept10Digits() {
        TaxCode taxCode = new TaxCode("0123456789");
        assertEquals("0123456789", taxCode.getValue());
    }

    @Test
    void shouldAccept13Digits() {
        TaxCode taxCode = new TaxCode("0123456789012");
        assertEquals("0123456789012", taxCode.getValue());
    }

    @Test
    void shouldRejectBlank() {
        assertThrows(ValidationException.class, () -> new TaxCode(null));
        assertThrows(ValidationException.class, () -> new TaxCode(""));
        assertThrows(ValidationException.class, () -> new TaxCode("   "));
    }

    @Test
    void shouldRejectInvalidLength() {
        assertThrows(ValidationException.class, () -> new TaxCode("123"));
        assertThrows(ValidationException.class, () -> new TaxCode("123456789"));
        assertThrows(ValidationException.class, () -> new TaxCode("123456789012"));
        assertThrows(ValidationException.class, () -> new TaxCode("12345678901234"));
    }

    @Test
    void shouldRejectNonDigits() {
        assertThrows(ValidationException.class, () -> new TaxCode("012345678a"));
        assertThrows(ValidationException.class, () -> new TaxCode("0123456789-01"));
    }

    @Test
    void shouldBeImmutableAndEqual() {
        TaxCode a = new TaxCode("0123456789");
        TaxCode b = new TaxCode("0123456789");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
