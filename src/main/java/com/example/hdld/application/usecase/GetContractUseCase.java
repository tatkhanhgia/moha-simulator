package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.GetContractRequest;
import com.example.hdld.application.dto.response.GetContractResponse;
import com.example.hdld.application.mapper.ContractMapper;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.ContractUuid;

/**
 * Retrieves a labor contract by its uuid (uuid_hop_dong) and returns it in the
 * platform's flat {@code data} shape.
 */
public class GetContractUseCase {

    private final LaborContractRepository contractRepository;

    public GetContractUseCase(LaborContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public GetContractResponse execute(GetContractRequest request) {
        LaborContract contract = contractRepository.findById(new ContractUuid(request.getContractUuid()))
                .orElseThrow(() -> new NotFoundException("Contract not found: " + request.getContractUuid()));

        String maGiaoDich = TransactionIdGenerator.generate("checkinfo");
        return new GetContractResponse(maGiaoDich, request.getContractUuid(), ContractMapper.toDetail(contract));
    }
}
