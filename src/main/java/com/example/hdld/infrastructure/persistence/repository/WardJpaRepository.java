package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.WardJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardJpaRepository extends JpaRepository<WardJpa, Long> {
    List<WardJpa> findByTinhThanhId(String tinhThanhId);
}
