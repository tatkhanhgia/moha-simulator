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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class LaborContractControllerIntegrationTest {

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
    void createContract_withAuth_shouldReturn200AndE00() throws Exception {
        String token = obtainToken();
        String json = "{\"doanhnghiep_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",\"thong_tin_nld\":{\"ho_ten\":\"Nguyen Van A\",\"ma_dinh_danh\":\"123456789\",\"ngay_sinh\":\"01011990\",\"gioi_tinh\":\"Nam\"},\"thong_tin_hop_dong\":{\"muc_luong\":1000,\"ngay_hieu_luc\":\"01012024\",\"ngay_het_hieu_luc\":\"31122024\",\"loai_hop_dong\":\"HDTV\"}}";

        mockMvc.perform(post("/hdld/ThemMoiHopDongLaoDong")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.transaction_id").isNotEmpty())
                .andExpect(jsonPath("$.hopdong_uuid").doesNotExist());
    }

    @Test
    void bulkCreate_with100Contracts_shouldReturn200AndE00() throws Exception {
        String token = obtainToken();
        String contract = "{\"doanhnghiep_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",\"thong_tin_nld\":{\"ho_ten\":\"Nguyen Van A\",\"ma_dinh_danh\":\"123456789\",\"ngay_sinh\":\"01011990\",\"gioi_tinh\":\"Nam\"},\"thong_tin_hop_dong\":{\"muc_luong\":1000,\"ngay_hieu_luc\":\"01012024\",\"loai_hop_dong\":\"HDTV\"}}";
        StringBuilder contracts = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            if (i > 0) contracts.append(",");
            contracts.append(contract);
        }
        String json = "{\"contracts\":[" + contracts + "]}";

        mockMvc.perform(post("/hdld/ThemMoiTheoLoHopDongLaoDong")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error_code").value("E00"))
                .andExpect(jsonPath("$.transaction_id").isNotEmpty());
    }

    @Test
    void bulkCreate_with101Contracts_shouldReturn400AndE02() throws Exception {
        String token = obtainToken();
        String contract = "{\"doanhnghiep_uuid\":\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",\"thong_tin_nld\":{\"ho_ten\":\"Nguyen Van A\",\"ma_dinh_danh\":\"123456789\",\"ngay_sinh\":\"01011990\",\"gioi_tinh\":\"Nam\"},\"thong_tin_hop_dong\":{\"muc_luong\":1000,\"ngay_hieu_luc\":\"01012024\",\"loai_hop_dong\":\"HDTV\"}}";
        StringBuilder contracts = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            if (i > 0) contracts.append(",");
            contracts.append(contract);
        }
        String json = "{\"contracts\":[" + contracts + "]}";

        mockMvc.perform(post("/hdld/ThemMoiTheoLoHopDongLaoDong")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_code").value("E02"));
    }
}
