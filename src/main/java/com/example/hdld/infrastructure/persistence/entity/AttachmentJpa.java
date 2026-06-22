package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "attachments")
public class AttachmentJpa {

    @Id
    @GeneratedValue
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "hop_dong_uuid", nullable = false)
    private UUID hopDongUuid;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "created_at")
    private Instant createdAt;

    public AttachmentJpa() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getHopDongUuid() {
        return hopDongUuid;
    }

    public void setHopDongUuid(UUID hopDongUuid) {
        this.hopDongUuid = hopDongUuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
