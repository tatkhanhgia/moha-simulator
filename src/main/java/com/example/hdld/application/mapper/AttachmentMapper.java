package com.example.hdld.application.mapper;

import com.example.hdld.application.dto.response.AttachmentResponse;
import com.example.hdld.domain.entity.Attachment;

/**
 * Maps between Attachment domain entity and DTOs.
 */
public class AttachmentMapper {

    private AttachmentMapper() {
    }

    public static AttachmentResponse toResponse(Attachment entity) {
        if (entity == null) {
            return null;
        }
        AttachmentResponse resp = new AttachmentResponse();
        resp.setAttachmentUuid(entity.getUuid() != null ? entity.getUuid().toString() : null);
        resp.setContractUuid(entity.getHopDongUuid() != null ? entity.getHopDongUuid().toString() : null);
        resp.setFileName(entity.getFileName());
        resp.setFilePath(entity.getFilePath());
        resp.setFileSize(entity.getFileSize());
        resp.setCreatedAt(entity.getCreatedAt());
        return resp;
    }
}
