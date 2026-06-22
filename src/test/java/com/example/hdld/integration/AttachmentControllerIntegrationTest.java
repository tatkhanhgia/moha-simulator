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

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AttachmentControllerIntegrationTest {

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
        return new ObjectMapper().readTree(body).path("data").path("token").asText();
    }

    @Test
    void upload_withValidBase64_shouldReturn200AndE00() throws Exception {
        String token = obtainToken();
        String base64 = Base64.getEncoder().encodeToString("PDF content".getBytes());
        String json = "{\"contract_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",\"file_name\":\"test.pdf\",\"base64_content\":\"" + base64 + "\"}";

        mockMvc.perform(post("/hdld/UploadFileHopDong")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"));
    }

    @Test
    void upload_withOversizedBase64_shouldReturn400AndE02() throws Exception {
        String token = obtainToken();
        byte[] oversized = new byte[5 * 1024 * 1024 + 1];
        String base64 = Base64.getEncoder().encodeToString(oversized);
        String json = "{\"contract_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",\"file_name\":\"big.pdf\",\"base64_content\":\"" + base64 + "\"}";

        mockMvc.perform(post("/hdld/UploadFileHopDong")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_code").value("E02"));
    }

    @Test
    void delete_withoutAuth_shouldReturn401AndE01() throws Exception {
        String json = "{\"attachment_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\"}";

        mockMvc.perform(post("/hdld/XoaFileHopDong")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }
}
