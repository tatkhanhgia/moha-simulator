package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for enterprise operations.
 */
public class EnterpriseResponse {

    @JsonProperty("enterprise_uuid")
    private String enterpriseUuid;

    @JsonProperty("ten_doanh_nghiep")
    private String tenDoanhNghiep;

    @JsonProperty("loai_hinh_doanh_nghiep")
    private String loaiHinhDoanhNghiep;

    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("ma_tinh")
    private String maTinh;

    @JsonProperty("ma_xa")
    private String maXa;

    @JsonProperty("nguoi_dai_dien")
    private String nguoiDaiDien;

    @JsonProperty("dien_thoai")
    private String dienThoai;

    @JsonProperty("fax")
    private String fax;

    @JsonProperty("email")
    private String email;

    @JsonProperty("website")
    private String website;

    @JsonProperty("ma_so_thue")
    private String maSoThue;

    @JsonProperty("ma_linh_vuc")
    private String maLinhVuc;

    @JsonProperty("ma_nganh_nghe")
    private String maNganhNghe;

    public EnterpriseResponse() {
    }

    public String getEnterpriseUuid() { return enterpriseUuid; }
    public void setEnterpriseUuid(String enterpriseUuid) { this.enterpriseUuid = enterpriseUuid; }

    public String getTenDoanhNghiep() { return tenDoanhNghiep; }
    public void setTenDoanhNghiep(String tenDoanhNghiep) { this.tenDoanhNghiep = tenDoanhNghiep; }

    public String getLoaiHinhDoanhNghiep() { return loaiHinhDoanhNghiep; }
    public void setLoaiHinhDoanhNghiep(String loaiHinhDoanhNghiep) { this.loaiHinhDoanhNghiep = loaiHinhDoanhNghiep; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getMaTinh() { return maTinh; }
    public void setMaTinh(String maTinh) { this.maTinh = maTinh; }

    public String getMaXa() { return maXa; }
    public void setMaXa(String maXa) { this.maXa = maXa; }

    public String getNguoiDaiDien() { return nguoiDaiDien; }
    public void setNguoiDaiDien(String nguoiDaiDien) { this.nguoiDaiDien = nguoiDaiDien; }

    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getMaSoThue() { return maSoThue; }
    public void setMaSoThue(String maSoThue) { this.maSoThue = maSoThue; }

    public String getMaLinhVuc() { return maLinhVuc; }
    public void setMaLinhVuc(String maLinhVuc) { this.maLinhVuc = maLinhVuc; }

    public String getMaNganhNghe() { return maNganhNghe; }
    public void setMaNganhNghe(String maNganhNghe) { this.maNganhNghe = maNganhNghe; }
}
