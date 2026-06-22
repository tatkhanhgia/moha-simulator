package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for ward reference data.
 */
public class WardResponse {

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("tinh_thanh_id")
    private String tinhThanhId;

    @JsonProperty("tinh_thanh_string")
    private String tinhThanhString;

    @JsonProperty("trang_thai")
    private String trangThai;

    public WardResponse() {
    }

    public WardResponse(String ma, String ten, String tinhThanhId, String tinhThanhString, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.tinhThanhId = tinhThanhId;
        this.tinhThanhString = tinhThanhString;
        this.trangThai = trangThai;
    }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getTinhThanhId() { return tinhThanhId; }
    public void setTinhThanhId(String tinhThanhId) { this.tinhThanhId = tinhThanhId; }

    public String getTinhThanhString() { return tinhThanhString; }
    public void setTinhThanhString(String tinhThanhString) { this.tinhThanhString = tinhThanhString; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
