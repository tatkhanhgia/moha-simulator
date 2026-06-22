package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wards")
public class WardJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma", nullable = false)
    private String ma;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "tinh_thanh_id")
    private String tinhThanhId;

    @Column(name = "tinh_thanh_string")
    private String tinhThanhString;

    @Column(name = "trang_thai")
    private String trangThai;

    public WardJpa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTinhThanhId() {
        return tinhThanhId;
    }

    public void setTinhThanhId(String tinhThanhId) {
        this.tinhThanhId = tinhThanhId;
    }

    public String getTinhThanhString() {
        return tinhThanhString;
    }

    public void setTinhThanhString(String tinhThanhString) {
        this.tinhThanhString = tinhThanhString;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
