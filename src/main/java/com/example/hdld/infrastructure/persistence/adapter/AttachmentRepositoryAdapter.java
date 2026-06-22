package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.infrastructure.persistence.entity.AttachmentJpa;
import com.example.hdld.infrastructure.persistence.repository.AttachmentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AttachmentRepositoryAdapter implements AttachmentRepository {

    private final AttachmentJpaRepository jpaRepository;

    public AttachmentRepositoryAdapter(AttachmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Attachment save(Attachment attachment) {
        AttachmentJpa jpa = toJpa(attachment);
        AttachmentJpa saved = jpaRepository.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<Attachment> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Attachment> findByContractId(ContractUuid contractId) {
        return jpaRepository.findByHopDongUuid(contractId.getValue())
                .stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private AttachmentJpa toJpa(Attachment domain) {
        AttachmentJpa jpa = new AttachmentJpa();
        jpa.setUuid(domain.getUuid() != null ? domain.getUuid() : UUID.randomUUID());
        jpa.setHopDongUuid(domain.getHopDongUuid() != null ? domain.getHopDongUuid().getValue() : null);
        jpa.setFileName(domain.getFileName());
        jpa.setFilePath(domain.getFilePath());
        jpa.setFileSize(domain.getFileSize());
        jpa.setCreatedAt(domain.getCreatedAt());
        return jpa;
    }

    private Attachment toDomain(AttachmentJpa jpa) {
        return new Attachment(
                jpa.getUuid(),
                jpa.getHopDongUuid() != null ? new ContractUuid(jpa.getHopDongUuid()) : null,
                jpa.getFileName(),
                jpa.getFilePath(),
                jpa.getFileSize(),
                jpa.getCreatedAt()
        );
    }
}
