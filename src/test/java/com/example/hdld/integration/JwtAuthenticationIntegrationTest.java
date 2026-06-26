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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class JwtAuthenticationIntegrationTest {

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
    void accessProtected_withValidToken_shouldReturn200() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/danh-muc-tinh/list")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void accessProtected_withInvalidToken_shouldReturn401AndE01() throws Exception {
        mockMvc.perform(get("/hdld/danh-muc-tinh/list")
                        .header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }

    @Test
    void accessProtected_withoutToken_shouldReturn401AndE01() throws Exception {
        mockMvc.perform(get("/hdld/danh-muc-tinh/list"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }
}
