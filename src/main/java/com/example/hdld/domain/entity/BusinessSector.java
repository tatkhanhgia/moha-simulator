package com.example.hdld.domain.entity;

/**
 * Reference entity representing a business sector (linh vuc kinh doanh).
 */
public class BusinessSector {

    private Long id;
    private String ma;
    private String ten;
    private String trangThai;

    public BusinessSector() {
    }

    public BusinessSector(Long id, String ma, String ten, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
