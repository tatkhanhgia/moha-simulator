package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "industries")
public class IndustryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma", nullable = false)
    private String ma;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "parent_string")
    private String parentString;

    @Column(name = "linh_vuc_kinh_doanh_id")
    private String linhVucKinhDoanhId;

    @Column(name = "linh_vuc_kinh_doanh_string")
    private String linhVucKinhDoanhString;

    @Column(name = "trang_thai")
    private String trangThai;

    public IndustryJpa() {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentString() {
        return parentString;
    }

    public void setParentString(String parentString) {
        this.parentString = parentString;
    }

    public String getLinhVucKinhDoanhId() {
        return linhVucKinhDoanhId;
    }

    public void setLinhVucKinhDoanhId(String linhVucKinhDoanhId) {
        this.linhVucKinhDoanhId = linhVucKinhDoanhId;
    }

    public String getLinhVucKinhDoanhString() {
        return linhVucKinhDoanhString;
    }

    public void setLinhVucKinhDoanhString(String linhVucKinhDoanhString) {
        this.linhVucKinhDoanhString = linhVucKinhDoanhString;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
