package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.domain.valueobject.TaxCode;

/**
 * Aggregate root representing an enterprise (doanh nghiep).
 */
public class Enterprise {

    private EnterpriseUuid uuid;
    private String tenDoanhNghiep;
    private String loaiHinhDoanhNghiep;
    private String diaChi;
    private String maTinh;
    private String maXa;
    private String nguoiDaiDien;
    private String dienThoai;
    private String fax;
    private Email email;
    private String website;
    private TaxCode maSoThue;
    private String maLinhVuc;
    private String maNganhNghe;

    public Enterprise() {
    }

    public Enterprise(EnterpriseUuid uuid, String tenDoanhNghiep, String loaiHinhDoanhNghiep,
                      String diaChi, String maTinh, String maXa, String nguoiDaiDien,
                      String dienThoai, String fax, Email email, String website,
                      TaxCode maSoThue, String maLinhVuc, String maNganhNghe) {
        this.uuid = uuid;
        this.tenDoanhNghiep = tenDoanhNghiep;
        this.loaiHinhDoanhNghiep = loaiHinhDoanhNghiep;
        this.diaChi = diaChi;
        this.maTinh = maTinh;
        this.maXa = maXa;
        this.nguoiDaiDien = nguoiDaiDien;
        this.dienThoai = dienThoai;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.maSoThue = maSoThue;
        this.maLinhVuc = maLinhVuc;
        this.maNganhNghe = maNganhNghe;
    }

    public EnterpriseUuid getUuid() {
        return uuid;
    }

    public void setUuid(EnterpriseUuid uuid) {
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public TaxCode getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(TaxCode maSoThue) {
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
}
