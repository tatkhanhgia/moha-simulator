package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.BusinessSector;
import com.example.hdld.domain.repository.BusinessSectorRepository;
import com.example.hdld.infrastructure.persistence.entity.BusinessSectorJpa;
import com.example.hdld.infrastructure.persistence.repository.BusinessSectorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessSectorRepositoryAdapter implements BusinessSectorRepository {

    private final BusinessSectorJpaRepository jpaRepository;

    public BusinessSectorRepositoryAdapter(BusinessSectorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<BusinessSector> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private BusinessSector toDomain(BusinessSectorJpa jpa) {
        return new BusinessSector(jpa.getId(), jpa.getMa(), jpa.getTen(), jpa.getTrangThai());
    }
}
