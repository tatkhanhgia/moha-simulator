package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UploadAttachmentRequest;
import com.example.hdld.application.dto.response.AttachmentResponse;
import com.example.hdld.application.mapper.AttachmentMapper;
import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.valueobject.ContractUuid;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * Uploads a Base64-encoded PDF attachment for a contract.
 * Validates decoded size <= 5MB.
 */
public class UploadAttachmentUseCase {

    private static final long MAX_SIZE_BYTES = 5L * 1024 * 1024;

    private final AttachmentRepository attachmentRepository;
    private final LaborContractRepository contractRepository;
    private final FileStoragePort fileStoragePort;

    public UploadAttachmentUseCase(AttachmentRepository attachmentRepository,
                                   LaborContractRepository contractRepository,
                                   FileStoragePort fileStoragePort) {
        this.attachmentRepository = attachmentRepository;
        this.contractRepository = contractRepository;
        this.fileStoragePort = fileStoragePort;
    }

    @Transactional
    public AttachmentResponse execute(UploadAttachmentRequest request) {
        ContractUuid contractUuid = new ContractUuid(request.getContractUuid());
        if (contractRepository.findById(contractUuid).isEmpty()) {
            throw new NotFoundException("Contract not found: " + request.getContractUuid());
        }

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(request.getBase64Content());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid Base64 content");
        }

        if (decoded.length > MAX_SIZE_BYTES) {
            throw new ValidationException("File size exceeds 5MB limit");
        }

        String path = fileStoragePort.store(decoded, request.getFileName());

        Attachment attachment = new Attachment(
                UUID.randomUUID(),
                contractUuid,
                request.getFileName(),
                path,
                decoded.length,
                Instant.now()
        );

        Attachment saved = attachmentRepository.save(attachment);
        return AttachmentMapper.toResponse(saved);
    }
}
