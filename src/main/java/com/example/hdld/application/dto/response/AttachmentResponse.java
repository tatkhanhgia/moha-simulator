package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * Response DTO for attachment operations.
 */
public class AttachmentResponse {

    @JsonProperty("attachment_uuid")
    private String attachmentUuid;

    @JsonProperty("contract_uuid")
    private String contractUuid;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("file_size")
    private long fileSize;

    @JsonProperty("created_at")
    private Instant createdAt;

    public AttachmentResponse() {
    }

    public String getAttachmentUuid() { return attachmentUuid; }
    public void setAttachmentUuid(String attachmentUuid) { this.attachmentUuid = attachmentUuid; }

    public String getContractUuid() { return contractUuid; }
    public void setContractUuid(String contractUuid) { this.contractUuid = contractUuid; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public long getFileSize() { return fileSize; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
