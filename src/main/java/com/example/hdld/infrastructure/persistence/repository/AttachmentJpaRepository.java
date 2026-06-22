package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.AttachmentJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttachmentJpaRepository extends JpaRepository<AttachmentJpa, UUID> {
    List<AttachmentJpa> findByHopDongUuid(UUID hopDongUuid);
}
