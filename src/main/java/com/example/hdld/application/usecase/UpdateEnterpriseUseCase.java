package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UpdateEnterpriseRequest;
import com.example.hdld.application.dto.response.EnterpriseResponse;
import com.example.hdld.application.mapper.EnterpriseMapper;
import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import org.springframework.transaction.annotation.Transactional;

/**
 * Updates an existing enterprise.
 */
public class UpdateEnterpriseUseCase {

    private final EnterpriseRepository enterpriseRepository;

    public UpdateEnterpriseUseCase(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Transactional
    public EnterpriseResponse execute(UpdateEnterpriseRequest request) {
        Enterprise enterprise = enterpriseRepository.findById(new EnterpriseUuid(request.getEnterpriseUuid()))
                .orElseThrow(() -> new NotFoundException("Enterprise not found: " + request.getEnterpriseUuid()));

        EnterpriseMapper.applyUpdate(enterprise, request);
        Enterprise saved = enterpriseRepository.save(enterprise);
        return EnterpriseMapper.toResponse(saved);
    }
}
