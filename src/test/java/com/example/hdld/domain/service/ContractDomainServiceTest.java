package com.example.hdld.domain.service;

import com.example.hdld.domain.entity.ContractInfo;
import com.example.hdld.domain.entity.EmployeeInfo;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ContractDomainServiceTest {

    private ContractDomainService service;

    @BeforeEach
    void setUp() {
        service = new ContractDomainService();
    }

    @Test
    void validateContractDates_shouldPassWhenEndAfterStart() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);
        assertThatNoException().isThrownBy(() -> service.validateContractDates(start, end));
    }

    @Test
    void validateContractDates_shouldPassWhenEndEqualsStart() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        assertThatNoException().isThrownBy(() -> service.validateContractDates(start, start));
    }

    @Test
    void validateContractDates_shouldPassWhenEndIsNull() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        assertThatNoException().isThrownBy(() -> service.validateContractDates(start, null));
    }

    @Test
    void validateContractDates_shouldThrowWhenStartIsNull() {
        assertThatThrownBy(() -> service.validateContractDates(null, LocalDate.now()))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contract start date is required");
    }

    @Test
    void validateContractDates_shouldThrowWhenEndBeforeStart() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 1, 1);
        assertThatThrownBy(() -> service.validateContractDates(start, end))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contract end date must be on or after start date");
    }

    @Test
    void validateSalary_shouldPassForPositiveSalary() {
        assertThatNoException().isThrownBy(() -> service.validateSalary(new BigDecimal("1000")));
    }

    @Test
    void validateSalary_shouldThrowForZeroSalary() {
        assertThatThrownBy(() -> service.validateSalary(BigDecimal.ZERO))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Salary must be greater than zero");
    }

    @Test
    void validateSalary_shouldThrowForNegativeSalary() {
        assertThatThrownBy(() -> service.validateSalary(new BigDecimal("-1")))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Salary must be greater than zero");
    }

    @Test
    void validateSalary_shouldThrowForNullSalary() {
        assertThatThrownBy(() -> service.validateSalary(null))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Salary must be greater than zero");
    }

    @Test
    void validateEmployeeInfo_shouldPassForValidInfo() {
        EmployeeInfo info = createValidEmployeeInfo();
        assertThatNoException().isThrownBy(() -> service.validateEmployeeInfo(info));
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenNull() {
        assertThatThrownBy(() -> service.validateEmployeeInfo(null))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee information is required");
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenNameMissing() {
        EmployeeInfo info = createValidEmployeeInfo();
        info.setHoTen("  ");
        assertThatThrownBy(() -> service.validateEmployeeInfo(info))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee full name is required");
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenIdMissing() {
        EmployeeInfo info = createValidEmployeeInfo();
        info.setMaDinhDanh(null);
        assertThatThrownBy(() -> service.validateEmployeeInfo(info))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee ID number is required");
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenIdInvalid() {
        EmployeeInfo info = createValidEmployeeInfo();
        info.setMaDinhDanh("12345");
        assertThatThrownBy(() -> service.validateEmployeeInfo(info))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee ID number must be 9 or 12 digits");
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenDobMissing() {
        EmployeeInfo info = createValidEmployeeInfo();
        info.setNgaySinh(null);
        assertThatThrownBy(() -> service.validateEmployeeInfo(info))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee date of birth is required");
    }

    @Test
    void validateEmployeeInfo_shouldThrowWhenGenderMissing() {
        EmployeeInfo info = createValidEmployeeInfo();
        info.setGioiTinh("");
        assertThatThrownBy(() -> service.validateEmployeeInfo(info))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee gender is required");
    }

    @Test
    void validateContract_shouldPassForValidContract() {
        LaborContract contract = createValidContract();
        assertThatNoException().isThrownBy(() -> service.validateContract(contract));
    }

    @Test
    void validateContract_shouldThrowWhenContractNull() {
        assertThatThrownBy(() -> service.validateContract(null))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contract is required");
    }

    @Test
    void validateContract_shouldThrowWhenContractInfoNull() {
        LaborContract contract = createValidContract();
        contract.setThongTinHopDong(null);
        assertThatThrownBy(() -> service.validateContract(contract))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Contract information is required");
    }

    private EmployeeInfo createValidEmployeeInfo() {
        return new EmployeeInfo(
                "Nguyen Van A",
                null,
                LocalDate.of(1990, 1, 1),
                "Nam",
                "123456789",
                null,
                null
        );
    }

    private LaborContract createValidContract() {
        ContractInfo info = new ContractInfo();
        info.setMucLuong(new BigDecimal("1000"));
        info.setNgayHieuLuc(LocalDate.of(2024, 1, 1));
        info.setNgayHetHieuLuc(LocalDate.of(2024, 12, 31));
        info.setLoaiHopDong("HDTV");

        return new LaborContract(
                new ContractUuid(java.util.UUID.randomUUID()),
                new EnterpriseUuid(java.util.UUID.randomUUID()),
                createValidEmployeeInfo(),
                info,
                "active"
        );
    }
}
