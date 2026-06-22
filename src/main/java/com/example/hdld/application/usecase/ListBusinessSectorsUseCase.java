package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.SectorResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.BusinessSectorRepository;

import java.util.List;

/**
 * Lists all business sectors.
 */
public class ListBusinessSectorsUseCase {

    private final BusinessSectorRepository businessSectorRepository;

    public ListBusinessSectorsUseCase(BusinessSectorRepository businessSectorRepository) {
        this.businessSectorRepository = businessSectorRepository;
    }

    public List<SectorResponse> execute() {
        return ReferenceDataMapper.toSectorList(businessSectorRepository.findAll());
    }
}
