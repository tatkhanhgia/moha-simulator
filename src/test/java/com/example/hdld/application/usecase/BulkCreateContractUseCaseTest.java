package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.BulkCreateContractRequest;
import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.CreateContractResponse;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BulkCreateContractUseCaseTest {

    @Mock
    private CreateContractUseCase createContractUseCase;

    private BulkCreateContractUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BulkCreateContractUseCase(createContractUseCase);
    }

    @Test
    void execute_shouldAcceptExactly100Contracts() {
        BulkCreateContractRequest request = new BulkCreateContractRequest();
        request.setContracts(createContractList(100));

        when(createContractUseCase.execute(any())).thenAnswer(inv -> {
            CreateContractRequest r = inv.getArgument(0);
            return new CreateContractResponse("TXN-" + r.getEnterpriseUuid(), "Thành công");
        });

        CreateContractResponse response = useCase.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.getTransactionId()).isNotBlank();
    }

    @Test
    void execute_shouldReject101Contracts() {
        BulkCreateContractRequest request = new BulkCreateContractRequest();
        request.setContracts(createContractList(101));

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Maximum 100 contracts per batch");
    }

    @Test
    void execute_shouldRejectEmptyList() {
        BulkCreateContractRequest request = new BulkCreateContractRequest();
        request.setContracts(new ArrayList<>());

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contracts list must not be empty");
    }

    @Test
    void execute_shouldRejectNullList() {
        BulkCreateContractRequest request = new BulkCreateContractRequest();
        request.setContracts(null);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contracts list must not be empty");
    }

    private List<CreateContractRequest> createContractList(int size) {
        List<CreateContractRequest> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createSingleContractRequest());
        }
        return list;
    }

    private CreateContractRequest createSingleContractRequest() {
        CreateContractRequest request = new CreateContractRequest();
        request.setEnterpriseUuid(java.util.UUID.randomUUID().toString());

        CreateContractRequest.EmployeeInfoRequest emp = new CreateContractRequest.EmployeeInfoRequest();
        emp.setHoTen("Nguyen Van A");
        emp.setMaDinhDanh("123456789");
        emp.setNgaySinh(LocalDate.of(1990, 1, 1));
        emp.setGioiTinh("Nam");
        request.setThongTinNld(emp);

        CreateContractRequest.ContractInfoRequest info = new CreateContractRequest.ContractInfoRequest();
        info.setMucLuong(new BigDecimal("1000"));
        info.setNgayHieuLuc(LocalDate.of(2024, 1, 1));
        info.setLoaiHopDong("HDTV");
        request.setThongTinHopDong(info);

        return request;
    }
}
