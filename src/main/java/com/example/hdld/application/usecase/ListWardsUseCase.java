package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.WardResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.WardRepository;

import java.util.List;

/**
 * Lists all wards or filters by province code.
 */
public class ListWardsUseCase {

    private final WardRepository wardRepository;

    public ListWardsUseCase(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<WardResponse> execute(String provinceCode) {
        if (provinceCode != null && !provinceCode.isBlank()) {
            return ReferenceDataMapper.toWardList(wardRepository.findByProvinceCode(provinceCode));
        }
        return ReferenceDataMapper.toWardList(wardRepository.findAll());
    }
}
