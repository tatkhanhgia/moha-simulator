package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EnterpriseUuidTest {

    @Test
    void shouldAcceptValidUuidString() {
        String raw = UUID.randomUUID().toString();
        EnterpriseUuid uuid = new EnterpriseUuid(raw);
        assertEquals(raw, uuid.toString());
    }

    @Test
    void shouldAcceptUuidObject() {
        UUID raw = UUID.randomUUID();
        EnterpriseUuid uuid = new EnterpriseUuid(raw);
        assertEquals(raw, uuid.getValue());
    }

    @Test
    void shouldRejectBlank() {
        assertThrows(ValidationException.class, () -> new EnterpriseUuid((String) null));
        assertThrows(ValidationException.class, () -> new EnterpriseUuid(""));
        assertThrows(ValidationException.class, () -> new EnterpriseUuid("   "));
    }

    @Test
    void shouldRejectInvalidUuid() {
        assertThrows(ValidationException.class, () -> new EnterpriseUuid("not-a-uuid"));
        assertThrows(ValidationException.class, () -> new EnterpriseUuid("12345"));
    }

    @Test
    void shouldRejectNullUuidObject() {
        assertThrows(ValidationException.class, () -> new EnterpriseUuid((UUID) null));
    }

    @Test
    void shouldBeImmutableAndEqual() {
        UUID raw = UUID.randomUUID();
        EnterpriseUuid a = new EnterpriseUuid(raw);
        EnterpriseUuid b = new EnterpriseUuid(raw);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
