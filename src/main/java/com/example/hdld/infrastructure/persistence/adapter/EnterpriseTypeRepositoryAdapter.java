package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.EnterpriseType;
import com.example.hdld.domain.repository.EnterpriseTypeRepository;
import com.example.hdld.infrastructure.persistence.entity.EnterpriseTypeJpa;
import com.example.hdld.infrastructure.persistence.repository.EnterpriseTypeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnterpriseTypeRepositoryAdapter implements EnterpriseTypeRepository {

    private final EnterpriseTypeJpaRepository jpaRepository;

    public EnterpriseTypeRepositoryAdapter(EnterpriseTypeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<EnterpriseType> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<EnterpriseType> findByCategory(String category) {
        return jpaRepository.findByLoai(category)
                .stream().map(this::toDomain).toList();
    }

    private EnterpriseType toDomain(EnterpriseTypeJpa jpa) {
        return new EnterpriseType(
                jpa.getId(),
                jpa.getMa(),
                jpa.getTen(),
                jpa.getLoai(),
                jpa.getTrangThai(),
                jpa.getTenTiengAnh()
        );
    }
}
