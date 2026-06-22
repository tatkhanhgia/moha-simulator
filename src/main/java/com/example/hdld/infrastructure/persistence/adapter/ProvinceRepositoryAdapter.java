package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Province;
import com.example.hdld.domain.repository.ProvinceRepository;
import com.example.hdld.infrastructure.persistence.entity.ProvinceJpa;
import com.example.hdld.infrastructure.persistence.repository.ProvinceJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvinceRepositoryAdapter implements ProvinceRepository {

    private final ProvinceJpaRepository jpaRepository;

    public ProvinceRepositoryAdapter(ProvinceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Province> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Province toDomain(ProvinceJpa jpa) {
        return new Province(jpa.getId(), jpa.getMa(), jpa.getTen(), jpa.getTrangThai());
    }
}
