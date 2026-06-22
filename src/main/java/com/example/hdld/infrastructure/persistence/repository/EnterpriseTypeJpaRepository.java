package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.EnterpriseTypeJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseTypeJpaRepository extends JpaRepository<EnterpriseTypeJpa, Long> {
    List<EnterpriseTypeJpa> findByLoai(String loai);
}
