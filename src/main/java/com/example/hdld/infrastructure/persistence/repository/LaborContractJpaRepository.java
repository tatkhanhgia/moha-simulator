package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.LaborContractJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LaborContractJpaRepository extends JpaRepository<LaborContractJpa, UUID> {
    List<LaborContractJpa> findByEnterpriseUuid(UUID enterpriseUuid);
}
