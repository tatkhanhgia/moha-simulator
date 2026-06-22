package com.example.hdld.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Value object embedded in LaborContract representing contract terms.
 */
public class ContractInfo {

    private String capBac;
    private String viTriLamViec;
    private BigDecimal mucLuong;
    private BigDecimal phuCapChucVu;
    private BigDecimal phuCapThamNien;
    private String thamNienNghe;
    private BigDecimal phuCapLuong;
    private BigDecimal cacKhoanBoSung;
    private LocalDate docHaiTuNgay;
    private LocalDate docHaiDenNgay;
    private String loaiHopDong;
    private String maSoHopDong;
    private String loaiBaoCao;
    private String uuidHopDong;
    private LocalDate ngayHieuLuc;
    private LocalDate ngayHetHieuLuc;
    private LocalDate thoiGianBatDauBhxh;
    private LocalDate thoiGianKetThucBhxh;
    private String ghiChu;
    private String phuongThucKyKet;

    public ContractInfo() {
    }

    public String getCapBac() {
        return capBac;
    }

    public void setCapBac(String capBac) {
        this.capBac = capBac;
    }

    public String getViTriLamViec() {
        return viTriLamViec;
    }

    public void setViTriLamViec(String viTriLamViec) {
        this.viTriLamViec = viTriLamViec;
    }

    public BigDecimal getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(BigDecimal mucLuong) {
        this.mucLuong = mucLuong;
    }

    public BigDecimal getPhuCapChucVu() {
        return phuCapChucVu;
    }

    public void setPhuCapChucVu(BigDecimal phuCapChucVu) {
        this.phuCapChucVu = phuCapChucVu;
    }

    public BigDecimal getPhuCapThamNien() {
        return phuCapThamNien;
    }

    public void setPhuCapThamNien(BigDecimal phuCapThamNien) {
        this.phuCapThamNien = phuCapThamNien;
    }

    public String getThamNienNghe() {
        return thamNienNghe;
    }

    public void setThamNienNghe(String thamNienNghe) {
        this.thamNienNghe = thamNienNghe;
    }

    public BigDecimal getPhuCapLuong() {
        return phuCapLuong;
    }

    public void setPhuCapLuong(BigDecimal phuCapLuong) {
        this.phuCapLuong = phuCapLuong;
    }

    public BigDecimal getCacKhoanBoSung() {
        return cacKhoanBoSung;
    }

    public void setCacKhoanBoSung(BigDecimal cacKhoanBoSung) {
        this.cacKhoanBoSung = cacKhoanBoSung;
    }

    public LocalDate getDocHaiTuNgay() {
        return docHaiTuNgay;
    }

    public void setDocHaiTuNgay(LocalDate docHaiTuNgay) {
        this.docHaiTuNgay = docHaiTuNgay;
    }

    public LocalDate getDocHaiDenNgay() {
        return docHaiDenNgay;
    }

    public void setDocHaiDenNgay(LocalDate docHaiDenNgay) {
        this.docHaiDenNgay = docHaiDenNgay;
    }

    public String getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(String loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
    }

    public String getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(String loaiBaoCao) {
        this.loaiBaoCao = loaiBaoCao;
    }

    public String getUuidHopDong() {
        return uuidHopDong;
    }

    public void setUuidHopDong(String uuidHopDong) {
        this.uuidHopDong = uuidHopDong;
    }

    public LocalDate getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(LocalDate ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public LocalDate getNgayHetHieuLuc() {
        return ngayHetHieuLuc;
    }

    public void setNgayHetHieuLuc(LocalDate ngayHetHieuLuc) {
        this.ngayHetHieuLuc = ngayHetHieuLuc;
    }

    public LocalDate getThoiGianBatDauBhxh() {
        return thoiGianBatDauBhxh;
    }

    public void setThoiGianBatDauBhxh(LocalDate thoiGianBatDauBhxh) {
        this.thoiGianBatDauBhxh = thoiGianBatDauBhxh;
    }

    public LocalDate getThoiGianKetThucBhxh() {
        return thoiGianKetThucBhxh;
    }

    public void setThoiGianKetThucBhxh(LocalDate thoiGianKetThucBhxh) {
        this.thoiGianKetThucBhxh = thoiGianKetThucBhxh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getPhuongThucKyKet() {
        return phuongThucKyKet;
    }

    public void setPhuongThucKyKet(String phuongThucKyKet) {
        this.phuongThucKyKet = phuongThucKyKet;
    }
}
