package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.GetContractRequest;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.application.mapper.ContractMapper;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.valueobject.ContractUuid;

/**
 * Retrieves a labor contract by its UUID.
 */
public class GetContractUseCase {

    private final LaborContractRepository contractRepository;

    public GetContractUseCase(LaborContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public ContractResponse execute(GetContractRequest request) {
        LaborContract contract = contractRepository.findById(new ContractUuid(request.getContractUuid()))
                .orElseThrow(() -> new NotFoundException("Contract not found: " + request.getContractUuid()));
        return ContractMapper.toResponse(contract);
    }
}
