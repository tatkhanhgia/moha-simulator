package com.example.hdld.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the HDLD date conventions: ddMMyyyy input, dd/MM/yyyy output, and blank
 * strings parsed as null (the latter was a root cause of the spurious E06 the gap
 * report flagged on PDF-conformant contract payloads).
 */
class JacksonDateConfigTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        new JacksonDateConfig().hdldDateCustomizer().customize(builder);
        mapper = builder.build();
    }

    @Test
    void deserialize_parsesCompactDdMmYyyy() throws Exception {
        Holder h = mapper.readValue("{\"ngay\":\"27041986\"}", Holder.class);
        assertThat(h.ngay).isEqualTo(LocalDate.of(1986, 4, 27));
    }

    @Test
    void deserialize_blankStringBecomesNull() throws Exception {
        Holder h = mapper.readValue("{\"ngay\":\"\"}", Holder.class);
        assertThat(h.ngay).isNull();
    }

    @Test
    void deserialize_alsoAcceptsSlashedDdMmYyyy() throws Exception {
        Holder h = mapper.readValue("{\"ngay\":\"16/05/1991\"}", Holder.class);
        assertThat(h.ngay).isEqualTo(LocalDate.of(1991, 5, 16));
    }

    @Test
    void serialize_writesSlashedDdMmYyyy() throws Exception {
        Holder h = new Holder();
        h.ngay = LocalDate.of(1986, 4, 27);
        assertThat(mapper.writeValueAsString(h)).contains("\"27/04/1986\"");
    }

    static class Holder {
        @JsonProperty("ngay")
        public LocalDate ngay;
    }
}
