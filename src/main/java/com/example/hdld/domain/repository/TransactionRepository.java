package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.valueobject.TransactionId;

import java.util.Optional;

/**
 * Port for transaction persistence operations.
 */
public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(TransactionId id);
}
