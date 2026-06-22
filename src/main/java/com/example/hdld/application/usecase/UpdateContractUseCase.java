package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UpdateContractRequest;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.application.mapper.ContractMapper;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.valueobject.ContractUuid;
import org.springframework.transaction.annotation.Transactional;

/**
 * Updates an existing labor contract.
 */
public class UpdateContractUseCase {

    private final LaborContractRepository contractRepository;

    public UpdateContractUseCase(LaborContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Transactional
    public ContractResponse execute(UpdateContractRequest request) {
        LaborContract contract = contractRepository.findById(new ContractUuid(request.getContractUuid()))
                .orElseThrow(() -> new NotFoundException("Contract not found: " + request.getContractUuid()));

        ContractMapper.applyUpdate(contract, request);
        LaborContract saved = contractRepository.save(contract);
        return ContractMapper.toResponse(saved);
    }
}
