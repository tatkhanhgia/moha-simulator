package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CheckTransactionRequest;
import com.example.hdld.application.dto.response.TransactionResponse;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.valueobject.TransactionId;

/**
 * Checks the status of a transaction by its ID.
 */
public class CheckTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public CheckTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse execute(CheckTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(new TransactionId(request.getTransactionId()))
                .orElseThrow(() -> new NotFoundException("Transaction not found: " + request.getTransactionId()));

        TransactionResponse resp = new TransactionResponse();
        resp.setTransactionId(transaction.getTransactionId().toString());
        resp.setLoaiGiaoDich(transaction.getLoaiGiaoDich());
        resp.setTrangThai(transaction.getTrangThai());
        resp.setThongBao(transaction.getThongBao());
        resp.setCreatedAt(transaction.getCreatedAt());
        resp.setUpdatedAt(transaction.getUpdatedAt());
        return resp;
    }
}
