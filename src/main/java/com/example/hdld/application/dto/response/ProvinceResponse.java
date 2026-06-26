package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for province reference data (GET /hdld/danh-muc-tinh/list).
 * Per the official contract each element exposes {@code id}, {@code ten}, {@code ma}.
 */
public class ProvinceResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("ma")
    private String ma;

    public ProvinceResponse() {
    }

    public ProvinceResponse(Long id, String ten, String ma) {
        this.id = id;
        this.ten = ten;
        this.ma = ma;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }
}
