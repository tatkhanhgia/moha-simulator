package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for business sector reference data (GET /hdld/linhvuckinhdoanh).
 * Per the official contract: {@code id, ma, ten, trang_thai}.
 */
public class SectorResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("trang_thai")
    private String trangThai;

    public SectorResponse() {
    }

    public SectorResponse(Long id, String ma, String ten, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
