package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.TransactionId;

import java.time.Instant;

/**
 * Entity representing a platform transaction (giao dich).
 */
public class Transaction {

    private TransactionId transactionId;
    private String loaiGiaoDich;
    private String trangThai;
    private String thongBao;
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
