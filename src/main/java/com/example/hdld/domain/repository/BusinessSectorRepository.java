package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.BusinessSector;

import java.util.List;

/**
 * Port for business sector reference data access.
 */
public interface BusinessSectorRepository {

    List<BusinessSector> findAll();
}
