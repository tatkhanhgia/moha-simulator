package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Industry;
import com.example.hdld.domain.repository.IndustryRepository;
import com.example.hdld.infrastructure.persistence.entity.IndustryJpa;
import com.example.hdld.infrastructure.persistence.repository.IndustryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndustryRepositoryAdapter implements IndustryRepository {

    private final IndustryJpaRepository jpaRepository;

    public IndustryRepositoryAdapter(IndustryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Industry> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Industry toDomain(IndustryJpa jpa) {
        return new Industry(
                jpa.getId(),
                jpa.getMa(),
                jpa.getTen(),
                jpa.getParentId(),
                jpa.getParentString(),
                jpa.getLinhVucKinhDoanhId(),
                jpa.getLinhVucKinhDoanhString(),
                jpa.getTrangThai()
        );
    }
}
