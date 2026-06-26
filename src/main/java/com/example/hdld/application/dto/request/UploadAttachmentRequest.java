package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for POST /hdld/UploadFileHopDongLaoDong.
 * Per the official contract: {@code uuid_hop_dong} + {@code file} (Base64 PDF).
 */
public class UploadAttachmentRequest {

    @NotBlank
    @JsonProperty("uuid_hop_dong")
    private String contractUuid;

    @NotBlank
    @JsonProperty("file")
    private String file;

    public UploadAttachmentRequest() {
    }

    public UploadAttachmentRequest(String contractUuid, String file) {
        this.contractUuid = contractUuid;
        this.file = file;
    }

    public String getContractUuid() {
        return contractUuid;
    }

    public void setContractUuid(String contractUuid) {
        this.contractUuid = contractUuid;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
