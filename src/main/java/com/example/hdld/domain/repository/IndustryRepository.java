package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Industry;

import java.util.List;

/**
 * Port for industry reference data access.
 */
public interface IndustryRepository {

    List<Industry> findAll();
}
