package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.EnterpriseType;

import java.util.List;

/**
 * Port for enterprise type reference data access.
 */
public interface EnterpriseTypeRepository {

    List<EnterpriseType> findAll();

    List<EnterpriseType> findByCategory(String category);
}
