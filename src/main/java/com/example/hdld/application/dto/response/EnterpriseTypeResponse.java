package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for enterprise type reference data (GET /hdld/danhmuc?loai=...).
 * Per the official contract: {@code id, ma, ten, loai, trang_thai, ten_tieng_anh}.
 */
public class EnterpriseTypeResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("loai")
    private String loai;

    @JsonProperty("trang_thai")
    private String trangThai;

    @JsonProperty("ten_tieng_anh")
    private String tenTiengAnh;

    public EnterpriseTypeResponse() {
    }

    public EnterpriseTypeResponse(Long id, String ma, String ten, String loai, String trangThai, String tenTiengAnh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.trangThai = trangThai;
        this.tenTiengAnh = tenTiengAnh;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getTenTiengAnh() { return tenTiengAnh; }
    public void setTenTiengAnh(String tenTiengAnh) { this.tenTiengAnh = tenTiengAnh; }
}
