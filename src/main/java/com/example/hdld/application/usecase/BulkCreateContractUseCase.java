package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.BulkCreateContractRequest;
import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.BulkContractResponse;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.domain.exception.DomainException;
import com.example.hdld.domain.exception.ValidationException;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Bulk creates labor contracts with all-or-nothing semantics.
 * Rejects batches larger than 100.
 */
public class BulkCreateContractUseCase {

    private final CreateContractUseCase createContractUseCase;

    public BulkCreateContractUseCase(CreateContractUseCase createContractUseCase) {
        this.createContractUseCase = createContractUseCase;
    }

    @Transactional
    public BulkContractResponse execute(BulkCreateContractRequest request) {
        List<CreateContractRequest> contracts = request.getContracts();
        if (contracts == null || contracts.isEmpty()) {
            throw new ValidationException("Contracts list must not be empty");
        }
        if (contracts.size() > 100) {
            throw new ValidationException("Maximum 100 contracts per batch");
        }

        List<ContractResponse> successes = new ArrayList<>();
        List<BulkContractResponse.BulkError> errors = new ArrayList<>();

        for (int i = 0; i < contracts.size(); i++) {
            try {
                ContractResponse resp = createContractUseCase.execute(contracts.get(i));
                successes.add(resp);
            } catch (DomainException e) {
                errors.add(new BulkContractResponse.BulkError(i, e.getErrorCode(), e.getMessage()));
            }
        }

        BulkContractResponse response = new BulkContractResponse();
        response.setTotal(contracts.size());
        response.setSuccessCount(successes.size());
        response.setFailureCount(errors.size());
        response.setContracts(successes);
        response.setErrors(errors);
        return response;
    }
}
