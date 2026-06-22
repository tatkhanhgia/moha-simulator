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
class EnterpriseControllerIntegrationTest {

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
    void createEnterprise_withAuth_shouldReturn200AndE00() throws Exception {
        String token = obtainToken();
        String json = "{\"ten_doanh_nghiep\":\"New Corp\",\"loai_hinh_doanh_nghiep\":\"DN\",\"dia_chi\":\"123 St\",\"ma_tinh\":\"01\",\"ma_xa\":\"001\",\"ma_so_thue\":\"9876543210\",\"ma_linh_vuc\":\"A\",\"ma_nganh_nghe\":\"011\"}";

        mockMvc.perform(post("/hdld/ThemMoiDoanhNghiep")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.data.ten_doanh_nghiep").value("New Corp"));
    }

    @Test
    void createEnterprise_withoutAuth_shouldReturn401AndE01() throws Exception {
        String json = "{\"ten_doanh_nghiep\":\"New Corp\",\"loai_hinh_doanh_nghiep\":\"DN\",\"dia_chi\":\"123 St\",\"ma_tinh\":\"01\",\"ma_xa\":\"001\",\"ma_so_thue\":\"9876543210\",\"ma_linh_vuc\":\"A\",\"ma_nganh_nghe\":\"011\"}";

        mockMvc.perform(post("/hdld/ThemMoiDoanhNghiep")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").value("E01"));
    }

    @Test
    void createEnterprise_withMissingFields_shouldReturn400AndE02() throws Exception {
        String token = obtainToken();
        String json = "{\"ten_doanh_nghiep\":\"\",\"loai_hinh_doanh_nghiep\":\"DN\",\"dia_chi\":\"123 St\",\"ma_tinh\":\"01\",\"ma_xa\":\"001\",\"ma_so_thue\":\"9876543210\",\"ma_linh_vuc\":\"A\",\"ma_nganh_nghe\":\"011\"}";

        mockMvc.perform(post("/hdld/ThemMoiDoanhNghiep")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_code").value("E02"));
    }
}
