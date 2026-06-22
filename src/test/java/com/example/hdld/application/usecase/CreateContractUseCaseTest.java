package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.service.ContractDomainService;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateContractUseCaseTest {

    @Mock
    private LaborContractRepository contractRepository;
    @Mock
    private EnterpriseRepository enterpriseRepository;

    private ContractDomainService contractDomainService;
    private CreateContractUseCase useCase;

    @BeforeEach
    void setUp() {
        contractDomainService = new ContractDomainService();
        useCase = new CreateContractUseCase(contractRepository, enterpriseRepository, contractDomainService);
    }

    @Test
    void execute_shouldCreateContractWhenEnterpriseExists() {
        String enterpriseUuid = UUID.randomUUID().toString();
        when(enterpriseRepository.findById(new EnterpriseUuid(enterpriseUuid)))
                .thenReturn(Optional.of(new Enterprise()));

        CreateContractRequest request = createValidRequest(enterpriseUuid);

        ArgumentCaptor<LaborContract> captor = ArgumentCaptor.forClass(LaborContract.class);
        when(contractRepository.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));

        ContractResponse response = useCase.execute(request);

        assertThat(response).isNotNull();
        assertThat(captor.getValue().getThongTinNld().getHoTen()).isEqualTo("Nguyen Van A");
    }

    @Test
    void execute_shouldThrowNotFoundWhenEnterpriseMissing() {
        String enterpriseUuid = UUID.randomUUID().toString();
        when(enterpriseRepository.findById(new EnterpriseUuid(enterpriseUuid)))
                .thenReturn(Optional.empty());

        CreateContractRequest request = createValidRequest(enterpriseUuid);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Enterprise not found");
    }

    @Test
    void execute_shouldThrowValidationWhenSalaryZero() {
        String enterpriseUuid = UUID.randomUUID().toString();
        when(enterpriseRepository.findById(new EnterpriseUuid(enterpriseUuid)))
                .thenReturn(Optional.of(new Enterprise()));

        CreateContractRequest request = createValidRequest(enterpriseUuid);
        request.getThongTinHopDong().setMucLuong(BigDecimal.ZERO);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Salary must be greater than zero");
    }

    private CreateContractRequest createValidRequest(String enterpriseUuid) {
        CreateContractRequest request = new CreateContractRequest();
        request.setEnterpriseUuid(enterpriseUuid);

        CreateContractRequest.EmployeeInfoRequest emp = new CreateContractRequest.EmployeeInfoRequest();
        emp.setHoTen("Nguyen Van A");
        emp.setMaDinhDanh("123456789");
        emp.setNgaySinh(LocalDate.of(1990, 1, 1));
        emp.setGioiTinh("Nam");
        request.setThongTinNld(emp);

        CreateContractRequest.ContractInfoRequest info = new CreateContractRequest.ContractInfoRequest();
        info.setMucLuong(new BigDecimal("1000"));
        info.setNgayHieuLuc(LocalDate.of(2024, 1, 1));
        info.setNgayHetHieuLuc(LocalDate.of(2024, 12, 31));
        info.setLoaiHopDong("HDTV");
        request.setThongTinHopDong(info);

        return request;
    }
}
