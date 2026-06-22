package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for industry reference data.
 */
public class IndustryResponse {

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("parent_string")
    private String parentString;

    @JsonProperty("linh_vuc_kinh_doanh_id")
    private String linhVucKinhDoanhId;

    @JsonProperty("linh_vuc_kinh_doanh_string")
    private String linhVucKinhDoanhString;

    @JsonProperty("trang_thai")
    private String trangThai;

    public IndustryResponse() {
    }

    public IndustryResponse(String ma, String ten, String parentId, String parentString,
                            String linhVucKinhDoanhId, String linhVucKinhDoanhString, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.parentId = parentId;
        this.parentString = parentString;
        this.linhVucKinhDoanhId = linhVucKinhDoanhId;
        this.linhVucKinhDoanhString = linhVucKinhDoanhString;
        this.trangThai = trangThai;
    }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public String getParentString() { return parentString; }
    public void setParentString(String parentString) { this.parentString = parentString; }

    public String getLinhVucKinhDoanhId() { return linhVucKinhDoanhId; }
    public void setLinhVucKinhDoanhId(String linhVucKinhDoanhId) { this.linhVucKinhDoanhId = linhVucKinhDoanhId; }

    public String getLinhVucKinhDoanhString() { return linhVucKinhDoanhString; }
    public void setLinhVucKinhDoanhString(String linhVucKinhDoanhString) { this.linhVucKinhDoanhString = linhVucKinhDoanhString; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
