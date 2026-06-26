package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.BulkCreateContractRequest;
import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.CreateContractResponse;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.service.TransactionIdGenerator;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Bulk creates labor contracts. Mirrors the platform's async acknowledgement: all
 * contracts are persisted, a single batch transaction ({@code insertlo_...}) records the
 * outcome, and the response returns that transaction id for the caller to poll.
 */
public class BulkCreateContractUseCase {

    private static final String ACK_MESSAGE =
            "Hệ thống đã ghi nhận dữ liệu, vui lòng kiểm tra kết quả ở api kiểm tra transaction_id";

    private final CreateContractUseCase createContractUseCase;

    public BulkCreateContractUseCase(CreateContractUseCase createContractUseCase) {
        this.createContractUseCase = createContractUseCase;
    }

    @Transactional
    public CreateContractResponse execute(BulkCreateContractRequest request) {
        List<CreateContractRequest> contracts = request.getContracts();
        if (contracts == null || contracts.isEmpty()) {
            throw new ValidationException("Contracts list must not be empty");
        }
        if (contracts.size() > 100) {
            throw new ValidationException("Maximum 100 contracts per batch");
        }

        for (CreateContractRequest contract : contracts) {
            LaborContract saved = createContractUseCase.persistContract(contract);
        }

        String transactionId = TransactionIdGenerator.generate(TransactionIdGenerator.INSERT_BATCH);
        createContractUseCase.saveSuccessTransaction(
                transactionId,
                TransactionIdGenerator.INSERT_BATCH,
                null,
                null,
                "Đã xử lý " + contracts.size() + " hợp đồng");
        return new CreateContractResponse(transactionId, ACK_MESSAGE);
    }
}
