package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.ProvinceResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.ProvinceRepository;

import java.util.List;

/**
 * Lists all provinces.
 */
public class ListProvincesUseCase {

    private final ProvinceRepository provinceRepository;

    public ListProvincesUseCase(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<ProvinceResponse> execute() {
        return ReferenceDataMapper.toProvinceList(provinceRepository.findAll());
    }
}
