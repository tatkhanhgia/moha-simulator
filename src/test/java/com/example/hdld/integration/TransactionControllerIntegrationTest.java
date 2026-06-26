package com.example.hdld.integration;

import com.example.hdld.application.dto.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String obtainToken() throws Exception {
        LoginRequest request = new LoginRequest("testuser", "password123");
        var result = mockMvc.perform(post("/hdld/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        return new ObjectMapper().readTree(body).path("token").asText();
    }

    @Test
    void checkTransaction_existing_shouldReturn200AndE00() throws Exception {
        String token = obtainToken();
        String json = "{\"ma_giao_dich\":\"TXN123\"}";

        mockMvc.perform(post("/hdld/KiemTraTrangThaiGiaoDich")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.ma_giao_dich").isNotEmpty())
                .andExpect(jsonPath("$.data[0].ma_giao_dich").value("TXN123"));
    }

    @Test
    void checkTransaction_missing_shouldReturn404AndE04() throws Exception {
        String token = obtainToken();
        String json = "{\"ma_giao_dich\":\"MISSING999\"}";

        mockMvc.perform(post("/hdld/KiemTraTrangThaiGiaoDich")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_code").value("E04"));
    }

    @Test
    void checkTransaction_withoutAuth_shouldReturn401AndE01() throws Exception {
        String json = "{\"ma_giao_dich\":\"TXN123\"}";

        mockMvc.perform(post("/hdld/KiemTraTrangThaiGiaoDich")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }
}
