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

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login_withValidCredentials_shouldReturnTokenAndE00() throws Exception {
        LoginRequest request = new LoginRequest("testuser", "password123");

        mockMvc.perform(post("/hdld/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.token").value(notNullValue()));
    }

    @Test
    void login_withInvalidPassword_shouldReturn401AndE01() throws Exception {
        LoginRequest request = new LoginRequest("testuser", "wrongpassword");

        mockMvc.perform(post("/hdld/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }

    @Test
    void login_withUnknownUser_shouldReturn401AndE01() throws Exception {
        LoginRequest request = new LoginRequest("nobody", "password123");

        mockMvc.perform(post("/hdld/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }

    @Test
    void changePassword_withoutAuth_shouldReturn401AndE01() throws Exception {
        String json = "{\"oldPassword\":\"old\",\"newPassword\":\"newpass123\"}";

        mockMvc.perform(post("/hdld/QuenMatKhau")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }
}
