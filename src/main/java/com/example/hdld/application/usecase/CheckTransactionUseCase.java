package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CheckTransactionRequest;
import com.example.hdld.application.dto.response.CheckTransactionResponse;
import com.example.hdld.application.dto.response.CheckTransactionResponse.TransactionDetail;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.TransactionId;

import java.util.List;

/**
 * Checks the status of a transaction by its id (ma_giao_dich) and returns the outcome
 * in the platform's {@code data[]} shape.
 */
public class CheckTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public CheckTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public CheckTransactionResponse execute(CheckTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(new TransactionId(request.getTransactionId()))
                .orElseThrow(() -> new NotFoundException("Transaction not found: " + request.getTransactionId()));

        TransactionDetail detail = new TransactionDetail(
                transaction.getTransactionId().toString(),
                transaction.getMaLoi(),
                transaction.getTrangThai(),
                transaction.getKetQuaXuLy(),
                transaction.getHopdongUuid(),
                transaction.getMaSoHopDong()
        );

        String checkId = TransactionIdGenerator.generate(TransactionIdGenerator.CHECK);
        return new CheckTransactionResponse(checkId, List.of(detail));
    }
}
