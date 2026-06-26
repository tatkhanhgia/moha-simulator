package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.WardPagingResponse;
import com.example.hdld.application.dto.response.WardResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.WardRepository;

import java.util.List;

/**
 * Lists wards, optionally filtered by province code, wrapped in the platform's
 * paging envelope ({@code total_count} + {@code data}).
 */
public class ListWardsUseCase {

    private final WardRepository wardRepository;

    public ListWardsUseCase(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public WardPagingResponse execute(String maTinh) {
        List<WardResponse> data;
        if (maTinh != null && !maTinh.isBlank()) {
            data = ReferenceDataMapper.toWardList(wardRepository.findByProvinceCode(maTinh));
        } else {
            data = ReferenceDataMapper.toWardList(wardRepository.findAll());
        }
        return new WardPagingResponse(data.size(), data);
    }
}
