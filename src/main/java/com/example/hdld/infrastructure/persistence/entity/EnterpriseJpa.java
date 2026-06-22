package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "enterprises")
public class EnterpriseJpa {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "ten_doanh_nghiep", nullable = false)
    private String tenDoanhNghiep;

    @Column(name = "loai_hinh_doanh_nghiep")
    private String loaiHinhDoanhNghiep;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ma_tinh")
    private String maTinh;

    @Column(name = "ma_xa")
    private String maXa;

    @Column(name = "nguoi_dai_dien")
    private String nguoiDaiDien;

    @Column(name = "dien_thoai")
    private String dienThoai;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "ma_so_thue")
    private String maSoThue;

    @Column(name = "ma_linh_vuc")
    private String maLinhVuc;

    @Column(name = "ma_nganh_nghe")
    private String maNganhNghe;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public EnterpriseJpa() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTenDoanhNghiep() {
        return tenDoanhNghiep;
    }

    public void setTenDoanhNghiep(String tenDoanhNghiep) {
        this.tenDoanhNghiep = tenDoanhNghiep;
    }

    public String getLoaiHinhDoanhNghiep() {
        return loaiHinhDoanhNghiep;
    }

    public void setLoaiHinhDoanhNghiep(String loaiHinhDoanhNghiep) {
        this.loaiHinhDoanhNghiep = loaiHinhDoanhNghiep;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getMaXa() {
        return maXa;
    }

    public void setMaXa(String maXa) {
        this.maXa = maXa;
    }

    public String getNguoiDaiDien() {
        return nguoiDaiDien;
    }

    public void setNguoiDaiDien(String nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getMaLinhVuc() {
        return maLinhVuc;
    }

    public void setMaLinhVuc(String maLinhVuc) {
        this.maLinhVuc = maLinhVuc;
    }

    public String getMaNganhNghe() {
        return maNganhNghe;
    }

    public void setMaNganhNghe(String maNganhNghe) {
        this.maNganhNghe = maNganhNghe;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
