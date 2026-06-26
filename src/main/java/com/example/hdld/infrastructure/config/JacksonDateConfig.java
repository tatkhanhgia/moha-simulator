package com.example.hdld.infrastructure.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Configures Jackson to (de)serialize {@link LocalDate} using the HDLD platform's
 * date conventions (see docs/pdf_extract.txt):
 * <ul>
 *   <li><b>Input</b>: {@code ddMMyyyy} compact strings, e.g. {@code "27041986"}.</li>
 *   <li><b>Output</b>: {@code dd/MM/yyyy}, e.g. {@code "27/04/1986"}.</li>
 * </ul>
 *
 * <p>Blank/empty date strings (the PDF sends {@code ""} for absent dates) are parsed
 * as {@code null} rather than throwing — previously these triggered a Jackson parse
 * failure that surfaced as a generic {@code E06}.
 *
 * <p>Registered via {@link Jackson2ObjectMapperBuilderCustomizer} so the existing
 * {@code SNAKE_CASE} naming strategy and {@code non_null} inclusion are preserved.
 */
@Configuration
public class JacksonDateConfig {

    /** Accepts {@code ddMMyyyy}; also tolerates {@code dd/MM/yyyy} for round-trips. */
    private static final DateTimeFormatter INPUT_COMPACT = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final DateTimeFormatter SLASHED = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer hdldDateCustomizer() {
        SimpleModule module = new SimpleModule("HdldDateModule");
        module.addSerializer(LocalDate.class, new HdldLocalDateSerializer());
        module.addDeserializer(LocalDate.class, new HdldLocalDateDeserializer());
        module.addDeserializer(BigDecimal.class, new BlankTolerantBigDecimalDeserializer());
        return builder -> builder.modulesToInstall(module);
    }

    static final class HdldLocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(SLASHED));
        }
    }

    static final class HdldLocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String raw = p.getValueAsString();
            if (raw == null || raw.trim().isEmpty()) {
                return null;
            }
            String text = raw.trim();
            if (text.contains("/")) {
                return LocalDate.parse(text, SLASHED);
            }
            return LocalDate.parse(text, INPUT_COMPACT);
        }
    }

    /**
     * The platform sends empty strings ({@code ""}) for absent numeric fields; treat those
     * as {@code null} instead of failing parsing (which would surface as a generic E06).
     */
    static final class BlankTolerantBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
        @Override
        public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String raw = p.getValueAsString();
            if (raw == null || raw.trim().isEmpty()) {
                return null;
            }
            return new BigDecimal(raw.trim());
        }
    }
}
