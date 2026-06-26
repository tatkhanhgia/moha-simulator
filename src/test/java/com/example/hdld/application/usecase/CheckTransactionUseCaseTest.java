package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CheckTransactionRequest;
import com.example.hdld.application.dto.response.CheckTransactionResponse;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.valueobject.TransactionId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    private CheckTransactionUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CheckTransactionUseCase(transactionRepository);
    }

    @Test
    void execute_shouldReturnTransactionWhenFound() {
        Transaction transaction = new Transaction(
                new TransactionId("TXN123"),
                "CREATE_CONTRACT",
                "completed",
                "Done",
                "E00",
                "Thành công",
                "hopdong-uuid",
                "MSHD001",
                Instant.now(),
                Instant.now()
        );
        when(transactionRepository.findById(new TransactionId("TXN123")))
                .thenReturn(Optional.of(transaction));

        CheckTransactionRequest request = new CheckTransactionRequest("TXN123");
        CheckTransactionResponse response = useCase.execute(request);

        assertThat(response.getMaGiaoDich()).isNotBlank();
        assertThat(response.getData()).hasSize(1);
        assertThat(response.getData().get(0).getMaGiaoDich()).isEqualTo("TXN123");
        assertThat(response.getData().get(0).getTrangThai()).isEqualTo("completed");
    }

    @Test
    void execute_shouldThrowNotFoundWhenMissing() {
        when(transactionRepository.findById(new TransactionId("MISSING")))
                .thenReturn(Optional.empty());

        CheckTransactionRequest request = new CheckTransactionRequest("MISSING");
        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Transaction not found");
    }
}
