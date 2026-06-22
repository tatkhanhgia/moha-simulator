package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Ward;
import com.example.hdld.domain.repository.WardRepository;
import com.example.hdld.infrastructure.persistence.entity.WardJpa;
import com.example.hdld.infrastructure.persistence.repository.WardJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WardRepositoryAdapter implements WardRepository {

    private final WardJpaRepository jpaRepository;

    public WardRepositoryAdapter(WardJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Ward> findByProvinceCode(String provinceCode) {
        return jpaRepository.findByTinhThanhId(provinceCode)
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<Ward> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Ward toDomain(WardJpa jpa) {
        return new Ward(
                jpa.getId(),
                jpa.getMa(),
                jpa.getTen(),
                jpa.getTinhThanhId(),
                jpa.getTinhThanhString(),
                jpa.getTrangThai()
        );
    }
}
