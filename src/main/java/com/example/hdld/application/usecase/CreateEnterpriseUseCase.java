package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CreateEnterpriseRequest;
import com.example.hdld.application.dto.response.EnterpriseResponse;
import com.example.hdld.application.mapper.EnterpriseMapper;
import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.service.EnterpriseDomainService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creates a new enterprise after validating uniqueness and required fields.
 */
public class CreateEnterpriseUseCase {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseDomainService enterpriseDomainService;

    public CreateEnterpriseUseCase(EnterpriseRepository enterpriseRepository,
                                   EnterpriseDomainService enterpriseDomainService) {
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseDomainService = enterpriseDomainService;
    }

    @Transactional
    public EnterpriseResponse execute(CreateEnterpriseRequest request) {
        Enterprise enterprise = EnterpriseMapper.toDomain(request);
        enterpriseDomainService.validateRequiredFields(enterprise);
        enterpriseDomainService.validateEmailFormat(
                enterprise.getEmail() != null ? enterprise.getEmail().toString() : null
        );
        if (enterprise.getMaSoThue() != null) {
            enterpriseDomainService.validateUniqueTaxCode(enterprise.getMaSoThue());
        }
        Enterprise saved = enterpriseRepository.save(enterprise);
        return EnterpriseMapper.toResponse(saved);
    }
}
