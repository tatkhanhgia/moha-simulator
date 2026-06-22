package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.DeleteAttachmentRequest;
import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.AttachmentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Deletes an attachment file and its metadata.
 */
public class DeleteAttachmentUseCase {

    private final AttachmentRepository attachmentRepository;
    private final FileStoragePort fileStoragePort;

    public DeleteAttachmentUseCase(AttachmentRepository attachmentRepository,
                                   FileStoragePort fileStoragePort) {
        this.attachmentRepository = attachmentRepository;
        this.fileStoragePort = fileStoragePort;
    }

    @Transactional
    public void execute(DeleteAttachmentRequest request) {
        UUID uuid = UUID.fromString(request.getAttachmentUuid());
        Attachment attachment = attachmentRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Attachment not found: " + request.getAttachmentUuid()));

        if (attachment.getFilePath() != null) {
            fileStoragePort.delete(attachment.getFilePath());
        }
        attachmentRepository.deleteById(uuid);
    }
}
