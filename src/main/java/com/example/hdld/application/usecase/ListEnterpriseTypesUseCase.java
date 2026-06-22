package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.response.EnterpriseTypeResponse;
import com.example.hdld.application.mapper.ReferenceDataMapper;
import com.example.hdld.domain.repository.EnterpriseTypeRepository;

import java.util.List;

/**
 * Lists all enterprise types or filters by category.
 */
public class ListEnterpriseTypesUseCase {

    private final EnterpriseTypeRepository enterpriseTypeRepository;

    public ListEnterpriseTypesUseCase(EnterpriseTypeRepository enterpriseTypeRepository) {
        this.enterpriseTypeRepository = enterpriseTypeRepository;
    }

    public List<EnterpriseTypeResponse> execute(String category) {
        if (category != null && !category.isBlank()) {
            return ReferenceDataMapper.toEnterpriseTypeList(
                    enterpriseTypeRepository.findByCategory(category)
            );
        }
        return ReferenceDataMapper.toEnterpriseTypeList(enterpriseTypeRepository.findAll());
    }
}
