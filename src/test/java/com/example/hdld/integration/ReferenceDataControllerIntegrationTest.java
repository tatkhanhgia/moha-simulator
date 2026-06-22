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
class ReferenceDataControllerIntegrationTest {

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
    void listProvinces_shouldReturn200AndList() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/tinhthanhpho")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void listWards_shouldReturn200AndList() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/xaphuong?province_code=01")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void listBusinessSectors_shouldReturn200AndList() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/linhvuckinhdoanh")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void listIndustries_shouldReturn200AndList() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/nganhnghe")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void listCategories_shouldReturn200AndList() throws Exception {
        String token = obtainToken();
        mockMvc.perform(get("/hdld/danhmuc?loai=loai_hinh")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void listProvinces_withoutAuth_shouldReturn401AndE01() throws Exception {
        mockMvc.perform(get("/hdld/tinhthanhpho"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }
}
