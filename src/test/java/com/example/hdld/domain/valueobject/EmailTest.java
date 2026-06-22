package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldAcceptValidEmail() {
        Email email = new Email("user@example.com");
        assertEquals("user@example.com", email.getValue());
    }

    @Test
    void shouldAcceptEmailWithDotsAndPlus() {
        Email email = new Email("first.last+tag@sub.domain.co");
        assertEquals("first.last+tag@sub.domain.co", email.getValue());
    }

    @Test
    void shouldRejectBlank() {
        assertThrows(ValidationException.class, () -> new Email(null));
        assertThrows(ValidationException.class, () -> new Email(""));
        assertThrows(ValidationException.class, () -> new Email("   "));
    }

    @Test
    void shouldRejectInvalidFormat() {
        assertThrows(ValidationException.class, () -> new Email("not-an-email"));
        assertThrows(ValidationException.class, () -> new Email("@example.com"));
        assertThrows(ValidationException.class, () -> new Email("user@"));
    }

    @Test
    void shouldBeImmutableAndEqual() {
        Email a = new Email("a@b.com");
        Email b = new Email("a@b.com");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
