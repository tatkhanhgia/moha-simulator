package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.Email;

import java.time.LocalDate;

/**
 * Value object embedded in LaborContract representing employee personal information.
 */
public class EmployeeInfo {

    private String hoTen;
    private String maSoBhxh;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String maDinhDanh;
    private Email email;
    private String dienThoai;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String hoTen, String maSoBhxh, LocalDate ngaySinh,
                        String gioiTinh, String maDinhDanh, Email email, String dienThoai) {
        this.hoTen = hoTen;
        this.maSoBhxh = maSoBhxh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maDinhDanh = maDinhDanh;
        this.email = email;
        this.dienThoai = dienThoai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaSoBhxh() {
        return maSoBhxh;
    }

    public void setMaSoBhxh(String maSoBhxh) {
        this.maSoBhxh = maSoBhxh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaDinhDanh() {
        return maDinhDanh;
    }

    public void setMaDinhDanh(String maDinhDanh) {
        this.maDinhDanh = maDinhDanh;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
