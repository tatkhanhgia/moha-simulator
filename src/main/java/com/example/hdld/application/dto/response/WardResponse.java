package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for ward reference data (an element of GET /hdld/xa-phuong/paging data[]).
 * Per the official contract: {@code id, ma, ten, ma_tinh, ten_tinh, trang_thai, tinh_id}.
 */
public class WardResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("ma_tinh")
    private String maTinh;

    @JsonProperty("ten_tinh")
    private String tenTinh;

    @JsonProperty("trang_thai")
    private String trangThai;

    @JsonProperty("tinh_id")
    private Long tinhId;

    public WardResponse() {
    }

    public WardResponse(Long id, String ma, String ten, String maTinh,
                        String tenTinh, String trangThai, Long tinhId) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
        this.trangThai = trangThai;
        this.tinhId = tinhId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getMaTinh() { return maTinh; }
    public void setMaTinh(String maTinh) { this.maTinh = maTinh; }

    public String getTenTinh() { return tenTinh; }
    public void setTenTinh(String tenTinh) { this.tenTinh = tenTinh; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public Long getTinhId() { return tinhId; }
    public void setTinhId(Long tinhId) { this.tinhId = tinhId; }
}
