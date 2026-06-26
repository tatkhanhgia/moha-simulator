package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.TransactionId;

import java.time.Instant;

/**
 * Entity representing a platform transaction (giao dich).
 *
 * <p>The platform processes write operations (create/update/upload/delete) asynchronously:
 * the write endpoint records a transaction and returns its id, and the caller polls
 * {@code KiemTraTrangThaiGiaoDich} to obtain the outcome. The outcome fields
 * ({@code maLoi}, {@code ketQuaXuLy}, {@code hopdongUuid}, {@code maSoHopDong}) carry
 * that result.
 */
public class Transaction {

    private TransactionId transactionId;
    private String loaiGiaoDich;
    private String trangThai;
    private String thongBao;
    private String maLoi;
    private String ketQuaXuLy;
    private String hopdongUuid;
    private String maSoHopDong;
    private Instant createdAt;
    private Instant updatedAt;

    public Transaction() {
    }

    public Transaction(TransactionId transactionId, String loaiGiaoDich,
                       String trangThai, String thongBao, Instant createdAt, Instant updatedAt) {
        this.transactionId = transactionId;
        this.loaiGiaoDich = loaiGiaoDich;
        this.trangThai = trangThai;
        this.thongBao = thongBao;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Transaction(TransactionId transactionId, String loaiGiaoDich, String trangThai,
                       String thongBao, String maLoi, String ketQuaXuLy, String hopdongUuid,
                       String maSoHopDong, Instant createdAt, Instant updatedAt) {
        this.transactionId = transactionId;
        this.loaiGiaoDich = loaiGiaoDich;
        this.trangThai = trangThai;
        this.thongBao = thongBao;
        this.maLoi = maLoi;
        this.ketQuaXuLy = ketQuaXuLy;
        this.hopdongUuid = hopdongUuid;
        this.maSoHopDong = maSoHopDong;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateStatus(String newStatus, String message) {
        this.trangThai = newStatus;
        this.thongBao = message;
        this.updatedAt = Instant.now();
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
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
