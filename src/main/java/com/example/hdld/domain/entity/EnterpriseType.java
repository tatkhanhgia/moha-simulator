package com.example.hdld.domain.entity;

/**
 * Reference entity representing an enterprise type (loai hinh doanh nghiep).
 */
public class EnterpriseType {

    private Long id;
    private String ma;
    private String ten;
    private String loai;
    private String trangThai;
    private String tenTiengAnh;

    public EnterpriseType() {
    }

    public EnterpriseType(Long id, String ma, String ten, String loai,
                          String trangThai, String tenTiengAnh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.trangThai = trangThai;
        this.tenTiengAnh = tenTiengAnh;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenTiengAnh() {
        return tenTiengAnh;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }
}
