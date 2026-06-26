package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.CreateContractResponse;
import com.example.hdld.application.mapper.ContractMapper;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.service.ContractDomainService;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.domain.valueobject.TransactionId;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Creates a new labor contract.
 *
 * <p>Mirrors the platform's asynchronous acknowledgement: the contract is persisted and
 * a {@link Transaction} record is written capturing the outcome, but the response only
 * returns the {@code transaction_id} (with {@code hopdong_uuid}/{@code mahopdong} null).
 * The caller obtains the result via {@code KiemTraTrangThaiGiaoDich}.
 */
public class CreateContractUseCase {

    private static final String ACK_MESSAGE =
            "Hệ thống đã ghi nhận dữ liệu, vui lòng kiểm tra kết quả ở api kiểm tra transaction_id";

    private final LaborContractRepository contractRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ContractDomainService contractDomainService;
    private final TransactionRepository transactionRepository;

    public CreateContractUseCase(LaborContractRepository contractRepository,
                                 EnterpriseRepository enterpriseRepository,
                                 ContractDomainService contractDomainService,
                                 TransactionRepository transactionRepository) {
        this.contractRepository = contractRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.contractDomainService = contractDomainService;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public CreateContractResponse execute(CreateContractRequest request) {
        String transactionId = recordContract(request, TransactionIdGenerator.INSERT);
        return new CreateContractResponse(transactionId, ACK_MESSAGE);
    }

    /**
     * Persists one contract, returning the saved aggregate. Performs enterprise-existence
     * and contract-rule validation. Does not create a transaction record.
     */
    public LaborContract persistContract(CreateContractRequest request) {
        EnterpriseUuid enterpriseUuid = new EnterpriseUuid(request.getEnterpriseUuid());
        if (enterpriseRepository.findById(enterpriseUuid).isEmpty()) {
            throw new NotFoundException("Enterprise not found: " + request.getEnterpriseUuid());
        }

        LaborContract contract = ContractMapper.toDomain(request);
        contractDomainService.validateContract(contract);
        return contractRepository.save(contract);
    }

    /**
     * Persists one contract and writes its transaction record, returning the generated
     * transaction id. Shared with bulk creation (which uses a different prefix).
     */
    String recordContract(CreateContractRequest request, String prefix) {
        LaborContract saved = persistContract(request);

        String hopdongUuid = saved.getUuid() != null ? saved.getUuid().toString() : null;
        String maSoHopDong = request.getThongTinHopDong() != null
                ? request.getThongTinHopDong().getMaSoHopDong() : null;

        String transactionId = TransactionIdGenerator.generate(prefix);
        saveSuccessTransaction(transactionId, prefix, hopdongUuid, maSoHopDong, "Thành công");
        return transactionId;
    }

    /** Writes a successful (E00) transaction record. */
    public void saveSuccessTransaction(String transactionId, String loaiGiaoDich,
                                       String hopdongUuid, String maSoHopDong, String ketQua) {
        Instant now = Instant.now();
        Transaction transaction = new Transaction(
                new TransactionId(transactionId),
                loaiGiaoDich,
                "Thành công",
                "Thành công",
                "E00",
                ketQua,
                hopdongUuid,
                maSoHopDong,
                now,
                now
        );
        transactionRepository.save(transaction);
    }
}
