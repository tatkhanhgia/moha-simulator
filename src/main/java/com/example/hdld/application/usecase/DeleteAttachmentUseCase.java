package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.DeleteAttachmentRequest;
import com.example.hdld.application.dto.response.DeleteFileResponse;
import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.TransactionId;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

/**
 * Deletes an attachment file (by uuid_file) and records a delete transaction.
 */
public class DeleteAttachmentUseCase {

    private final AttachmentRepository attachmentRepository;
    private final FileStoragePort fileStoragePort;
    private final TransactionRepository transactionRepository;

    public DeleteAttachmentUseCase(AttachmentRepository attachmentRepository,
                                   FileStoragePort fileStoragePort,
                                   TransactionRepository transactionRepository) {
        this.attachmentRepository = attachmentRepository;
        this.fileStoragePort = fileStoragePort;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public DeleteFileResponse execute(DeleteAttachmentRequest request) {
        UUID uuid = UUID.fromString(request.getUuidFile());
        Attachment attachment = attachmentRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Attachment not found: " + request.getUuidFile()));

        if (attachment.getFilePath() != null) {
            fileStoragePort.delete(attachment.getFilePath());
        }
        attachmentRepository.deleteById(uuid);

        String transactionId = TransactionIdGenerator.generate(TransactionIdGenerator.DELETE_FILE);
        Instant now = Instant.now();
        transactionRepository.save(new Transaction(
                new TransactionId(transactionId),
                TransactionIdGenerator.DELETE_FILE,
                "Thành công",
                "Thành công",
                "E00",
                "Thành công",
                request.getContractUuid(),
                null,
                now,
                now
        ));

        return new DeleteFileResponse(transactionId, request.getUuidFile());
    }
}
