package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transactions")
public class TransactionJpa {

    @Id
    @Column(name = "transaction_id", nullable = false, length = 40)
    private String transactionId;

    @Column(name = "loai_giao_dich")
    private String loaiGiaoDich;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "thong_bao", length = 1000)
    private String thongBao;

    @Column(name = "ma_loi")
    private String maLoi;

    @Column(name = "ket_qua_xu_ly", length = 1000)
    private String ketQuaXuLy;

    @Column(name = "hopdong_uuid", length = 64)
    private String hopdongUuid;

    @Column(name = "ma_so_hop_dong")
    private String maSoHopDong;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public TransactionJpa() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getKetQuaXuLy() {
        return ketQuaXuLy;
    }

    public void setKetQuaXuLy(String ketQuaXuLy) {
        this.ketQuaXuLy = ketQuaXuLy;
    }

    public String getHopdongUuid() {
        return hopdongUuid;
    }

    public void setHopdongUuid(String hopdongUuid) {
        this.hopdongUuid = hopdongUuid;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
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
