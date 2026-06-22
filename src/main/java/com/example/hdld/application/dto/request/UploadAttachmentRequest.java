package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for POST /hdld/UploadFileHopDong
 */
public class UploadAttachmentRequest {

    @NotBlank
    @JsonProperty("contract_uuid")
    private String contractUuid;

    @NotBlank
    @JsonProperty("file_name")
    private String fileName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+/=]+$", message = "Base64 content format invalid")
    @JsonProperty("base64_content")
    private String base64Content;

    public UploadAttachmentRequest() {
    }

    public UploadAttachmentRequest(String contractUuid, String fileName, String base64Content) {
        this.contractUuid = contractUuid;
        this.fileName = fileName;
        this.base64Content = base64Content;
    }

    public String getContractUuid() {
        return contractUuid;
    }

    public void setContractUuid(String contractUuid) {
        this.contractUuid = contractUuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBase64Content() {
        return base64Content;
    }

    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }
}
