package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.valueobject.EnterpriseUuid;

import java.util.List;
import java.util.Optional;

/**
 * Port for enterprise persistence operations.
 */
public interface EnterpriseRepository {

    Enterprise save(Enterprise enterprise);

    Optional<Enterprise> findById(EnterpriseUuid id);

    Optional<Enterprise> findByMaSoThue(String maSoThue);

    List<Enterprise> findAll();

    void deleteById(EnterpriseUuid id);
}
