package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.valueobject.TransactionId;
import com.example.hdld.infrastructure.persistence.entity.TransactionJpa;
import com.example.hdld.infrastructure.persistence.repository.TransactionJpaRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionJpa jpa = toJpa(transaction);
        if (jpa.getCreatedAt() == null) {
            jpa.setCreatedAt(Instant.now());
        }
        jpa.setUpdatedAt(Instant.now());
        TransactionJpa saved = jpaRepository.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(TransactionId id) {
        return jpaRepository.findById(id.getValue()).map(this::toDomain);
    }

    private TransactionJpa toJpa(Transaction domain) {
        TransactionJpa jpa = new TransactionJpa();
        jpa.setTransactionId(domain.getTransactionId().getValue());
        jpa.setLoaiGiaoDich(domain.getLoaiGiaoDich());
        jpa.setTrangThai(domain.getTrangThai());
        jpa.setThongBao(domain.getThongBao());
        jpa.setCreatedAt(domain.getCreatedAt());
        jpa.setUpdatedAt(domain.getUpdatedAt());
        return jpa;
    }

    private Transaction toDomain(TransactionJpa jpa) {
        return new Transaction(
                new TransactionId(jpa.getTransactionId()),
                jpa.getLoaiGiaoDich(),
                jpa.getTrangThai(),
                jpa.getThongBao(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt()
        );
    }
}
