package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.TransactionJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, String> {
}
