package com.example.hdld.domain.entity;

/**
 * Reference entity representing a ward (xa phuong).
 */
public class Ward {

    private Long id;
    private String ma;
    private String ten;
    private String tinhThanhId;
    private String tinhThanhString;
    private String trangThai;

    public Ward() {
    }

    public Ward(Long id, String ma, String ten, String tinhThanhId,
                String tinhThanhString, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tinhThanhId = tinhThanhId;
        this.tinhThanhString = tinhThanhString;
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
