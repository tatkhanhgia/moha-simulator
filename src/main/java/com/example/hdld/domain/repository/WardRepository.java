package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Ward;

import java.util.List;

/**
 * Port for ward reference data access.
 */
public interface WardRepository {

    List<Ward> findByProvinceCode(String provinceCode);

    List<Ward> findAll();
}
