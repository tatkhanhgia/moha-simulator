package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.application.mapper.ContractMapper;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.service.ContractDomainService;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creates a new labor contract after validating enterprise existence and contract rules.
 */
public class CreateContractUseCase {

    private final LaborContractRepository contractRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ContractDomainService contractDomainService;

    public CreateContractUseCase(LaborContractRepository contractRepository,
                                 EnterpriseRepository enterpriseRepository,
                                 ContractDomainService contractDomainService) {
        this.contractRepository = contractRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.contractDomainService = contractDomainService;
    }

    @Transactional
    public ContractResponse execute(CreateContractRequest request) {
        EnterpriseUuid enterpriseUuid = new EnterpriseUuid(request.getEnterpriseUuid());
        if (enterpriseRepository.findById(enterpriseUuid).isEmpty()) {
            throw new NotFoundException("Enterprise not found: " + request.getEnterpriseUuid());
        }

        LaborContract contract = ContractMapper.toDomain(request);
        contractDomainService.validateContract(contract);
        LaborContract saved = contractRepository.save(contract);
        return ContractMapper.toResponse(saved);
    }
}
