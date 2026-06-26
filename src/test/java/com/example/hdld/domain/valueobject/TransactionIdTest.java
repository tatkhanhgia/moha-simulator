package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionIdTest {

    @Test
    void shouldAcceptAlphanumeric() {
        TransactionId txId = new TransactionId("TX123abc");
        assertEquals("TX123abc", txId.getValue());
    }

    @Test
    void shouldAcceptMaxLength40() {
        TransactionId txId = new TransactionId("A".repeat(40));
        assertEquals(40, txId.getValue().length());
    }

    @Test
    void shouldRejectBlank() {
        assertThrows(ValidationException.class, () -> new TransactionId(null));
        assertThrows(ValidationException.class, () -> new TransactionId(""));
        assertThrows(ValidationException.class, () -> new TransactionId("   "));
    }

    @Test
    void shouldRejectTooLong() {
        assertThrows(ValidationException.class, () -> new TransactionId("A".repeat(41)));
    }

    @Test
    void shouldRejectNonAlphanumericOrUnderscore() {
        assertThrows(ValidationException.class, () -> new TransactionId("TX-123"));
        assertThrows(ValidationException.class, () -> new TransactionId("TX 123"));
    }

    @Test
    void shouldAcceptUnderscore() {
        TransactionId txId = new TransactionId("TX_123");
        assertEquals("TX_123", txId.getValue());
    }

    @Test
    void shouldBeImmutableAndEqual() {
        TransactionId a = new TransactionId("ABC123");
        TransactionId b = new TransactionId("ABC123");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
