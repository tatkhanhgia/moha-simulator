package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for POST /hdld/ThemMoiDoanhNghiep
 */
public class CreateEnterpriseRequest {

    @NotBlank
    @Size(max = 250)
    @JsonProperty("ten_doanh_nghiep")
    private String tenDoanhNghiep;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("loai_hinh_doanh_nghiep")
    private String loaiHinhDoanhNghiep;

    @NotBlank
    @Size(max = 250)
    @JsonProperty("dia_chi")
    private String diaChi;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("ma_tinh")
    private String maTinh;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("ma_xa")
    private String maXa;

    @Size(max = 250)
    @JsonProperty("nguoi_dai_dien")
    private String nguoiDaiDien;

    @Size(max = 20)
    @JsonProperty("dien_thoai")
    private String dienThoai;

    @Size(max = 20)
    @JsonProperty("fax")
    private String fax;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email format invalid")
    @Size(max = 100)
    @JsonProperty("email")
    private String email;

    @Size(max = 200)
    @JsonProperty("website")
    private String website;

    @NotBlank
    @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "Tax code must be 10 or 13 digits")
    @Size(max = 20)
    @JsonProperty("ma_so_thue")
    private String maSoThue;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("ma_linh_vuc")
    private String maLinhVuc;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("ma_nganh_nghe")
    private String maNganhNghe;

    public CreateEnterpriseRequest() {
    }

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
