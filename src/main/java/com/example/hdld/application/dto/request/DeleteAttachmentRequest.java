package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for POST /hdld/XoaFileHopDong
 */
public class DeleteAttachmentRequest {

    @NotBlank
    @JsonProperty("attachment_uuid")
    private String attachmentUuid;

    public DeleteAttachmentRequest() {
    }

    public DeleteAttachmentRequest(String attachmentUuid) {
        this.attachmentUuid = attachmentUuid;
    }

    public String getAttachmentUuid() {
        return attachmentUuid;
    }

    public void setAttachmentUuid(String attachmentUuid) {
        this.attachmentUuid = attachmentUuid;
    }
}
