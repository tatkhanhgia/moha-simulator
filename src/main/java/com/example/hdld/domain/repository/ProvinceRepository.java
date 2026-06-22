package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Province;

import java.util.List;

/**
 * Port for province reference data access.
 */
public interface ProvinceRepository {

    List<Province> findAll();
}
