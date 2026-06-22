package com.example.hdld.domain.valueobject;

import com.example.hdld.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ContractUuidTest {

    @Test
    void shouldAcceptValidUuidString() {
        String raw = UUID.randomUUID().toString();
        ContractUuid uuid = new ContractUuid(raw);
        assertEquals(raw, uuid.toString());
    }

    @Test
    void shouldAcceptUuidObject() {
        UUID raw = UUID.randomUUID();
        ContractUuid uuid = new ContractUuid(raw);
        assertEquals(raw, uuid.getValue());
    }

    @Test
    void shouldRejectBlank() {
        assertThrows(ValidationException.class, () -> new ContractUuid((String) null));
        assertThrows(ValidationException.class, () -> new ContractUuid(""));
        assertThrows(ValidationException.class, () -> new ContractUuid("   "));
    }

    @Test
    void shouldRejectInvalidUuid() {
        assertThrows(ValidationException.class, () -> new ContractUuid("not-a-uuid"));
        assertThrows(ValidationException.class, () -> new ContractUuid("12345"));
    }

    @Test
    void shouldRejectNullUuidObject() {
        assertThrows(ValidationException.class, () -> new ContractUuid((UUID) null));
    }

    @Test
    void shouldBeImmutableAndEqual() {
        UUID raw = UUID.randomUUID();
        ContractUuid a = new ContractUuid(raw);
        ContractUuid b = new ContractUuid(raw);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
