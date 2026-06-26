package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for POST /hdld/XoaFileHopDong.
 * Per the official contract: {@code uuid_hop_dong} + {@code uuid_file}.
 */
public class DeleteAttachmentRequest {

    @NotBlank
    @JsonProperty("uuid_hop_dong")
    private String contractUuid;

    @NotBlank
    @JsonProperty("uuid_file")
    private String uuidFile;

    public DeleteAttachmentRequest() {
    }

    public DeleteAttachmentRequest(String contractUuid, String uuidFile) {
        this.contractUuid = contractUuid;
        this.uuidFile = uuidFile;
    }

    public String getContractUuid() {
        return contractUuid;
    }

    public void setContractUuid(String contractUuid) {
        this.contractUuid = contractUuid;
    }

    public String getUuidFile() {
        return uuidFile;
    }

    public void setUuidFile(String uuidFile) {
        this.uuidFile = uuidFile;
    }
}
