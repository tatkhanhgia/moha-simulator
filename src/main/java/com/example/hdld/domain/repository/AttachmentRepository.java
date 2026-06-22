package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.valueobject.ContractUuid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port for attachment persistence operations.
 */
public interface AttachmentRepository {

    Attachment save(Attachment attachment);

    Optional<Attachment> findById(UUID id);

    List<Attachment> findByContractId(ContractUuid contractId);

    void deleteById(UUID id);
}
