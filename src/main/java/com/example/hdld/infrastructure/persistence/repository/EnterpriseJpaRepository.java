package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.EnterpriseJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnterpriseJpaRepository extends JpaRepository<EnterpriseJpa, UUID> {
    Optional<EnterpriseJpa> findByMaSoThue(String maSoThue);
}
