package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UploadAttachmentRequest;
import com.example.hdld.application.dto.response.UploadFileResponse;
import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.TransactionId;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * Uploads a Base64-encoded PDF attachment for a contract and records an upload
 * transaction. Returns the transaction id and the stored file uuid at the root.
 */
public class UploadAttachmentUseCase {

    private static final long MAX_SIZE_BYTES = 5L * 1024 * 1024;

    private final AttachmentRepository attachmentRepository;
    private final LaborContractRepository contractRepository;
    private final FileStoragePort fileStoragePort;
    private final TransactionRepository transactionRepository;

    public UploadAttachmentUseCase(AttachmentRepository attachmentRepository,
                                   LaborContractRepository contractRepository,
                                   FileStoragePort fileStoragePort,
                                   TransactionRepository transactionRepository) {
        this.attachmentRepository = attachmentRepository;
        this.contractRepository = contractRepository;
        this.fileStoragePort = fileStoragePort;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public UploadFileResponse execute(UploadAttachmentRequest request) {
        ContractUuid contractUuid = new ContractUuid(request.getContractUuid());
        if (contractRepository.findById(contractUuid).isEmpty()) {
            throw new NotFoundException("Contract not found: " + request.getContractUuid());
        }

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(request.getFile());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid Base64 content");
        }

        if (decoded.length > MAX_SIZE_BYTES) {
            throw new ValidationException("File size exceeds 5MB limit");
        }

        UUID attachmentUuid = UUID.randomUUID();
        String fileName = attachmentUuid + ".pdf";
        String path = fileStoragePort.store(decoded, fileName);

        Attachment attachment = new Attachment(
                attachmentUuid,
                contractUuid,
                fileName,
                path,
                decoded.length,
                Instant.now()
        );
        Attachment saved = attachmentRepository.save(attachment);
        String uuidFile = saved.getUuid().toString();

        String transactionId = TransactionIdGenerator.generate(TransactionIdGenerator.UPLOAD);
        Instant now = Instant.now();
        transactionRepository.save(new Transaction(
                new TransactionId(transactionId),
                TransactionIdGenerator.UPLOAD,
                "Thành công",
                "Thành công",
                "E00",
                "Thành công",
                request.getContractUuid(),
                null,
                now,
                now
        ));

        return new UploadFileResponse(transactionId, uuidFile);
    }
}
