package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.IndustryResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.IndustryRepository;

import java.util.List;

/**
 * Lists all industries.
 */
public class ListIndustriesUseCase {

    private final IndustryRepository industryRepository;

    public ListIndustriesUseCase(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public List<IndustryResponse> execute() {
        return ReferenceDataMapper.toIndustryList(industryRepository.findAll());
    }
}
