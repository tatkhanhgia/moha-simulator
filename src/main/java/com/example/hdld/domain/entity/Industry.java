package com.example.hdld.domain.entity;

/**
 * Reference entity representing an industry (nganh nghe).
 */
public class Industry {

    private Long id;
    private String ma;
    private String ten;
    private String parentId;
    private String parentString;
    private String linhVucKinhDoanhId;
    private String linhVucKinhDoanhString;
    private String trangThai;

    public Industry() {
    }

    public Industry(Long id, String ma, String ten, String parentId,
                    String parentString, String linhVucKinhDoanhId,
                    String linhVucKinhDoanhString, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.parentId = parentId;
        this.parentString = parentString;
        this.linhVucKinhDoanhId = linhVucKinhDoanhId;
        this.linhVucKinhDoanhString = linhVucKinhDoanhString;
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
