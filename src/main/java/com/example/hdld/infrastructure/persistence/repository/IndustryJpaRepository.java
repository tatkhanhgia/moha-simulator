package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.IndustryJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryJpaRepository extends JpaRepository<IndustryJpa, Long> {
}
