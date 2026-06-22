package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.ContractUuid;

import java.time.Instant;
import java.util.UUID;

/**
 * Entity representing a contract attachment file.
 */
public class Attachment {

    private UUID uuid;
    private ContractUuid hopDongUuid;
    private String fileName;
    private String filePath;
    private long fileSize;
    private Instant createdAt;

    public Attachment() {
    }

    public Attachment(UUID uuid, ContractUuid hopDongUuid, String fileName,
                      String filePath, long fileSize, Instant createdAt) {
        this.uuid = uuid;
        this.hopDongUuid = hopDongUuid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.createdAt = createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ContractUuid getHopDongUuid() {
        return hopDongUuid;
    }

    public void setHopDongUuid(ContractUuid hopDongUuid) {
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
