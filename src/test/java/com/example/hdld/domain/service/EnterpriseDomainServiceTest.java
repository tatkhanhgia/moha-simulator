package com.example.hdld.domain.service;

import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.domain.valueobject.TaxCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ExtendWith(MockitoExtension.class)
class EnterpriseDomainServiceTest {

    @Mock
    private EnterpriseRepository enterpriseRepository;

    private EnterpriseDomainService service;

    @BeforeEach
    void setUp() {
        service = new EnterpriseDomainService(enterpriseRepository);
    }

    @Test
    void validateEmailFormat_shouldPassForValidEmail() {
        assertThatNoException().isThrownBy(() -> service.validateEmailFormat("test@example.com"));
    }

    @Test
    void validateEmailFormat_shouldPassForNullEmail() {
        assertThatNoException().isThrownBy(() -> service.validateEmailFormat(null));
    }

    @Test
    void validateEmailFormat_shouldPassForBlankEmail() {
        assertThatNoException().isThrownBy(() -> service.validateEmailFormat("  "));
    }

    @Test
    void validateEmailFormat_shouldThrowForInvalidEmail() {
        assertThatThrownBy(() -> service.validateEmailFormat("not-an-email"))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Email format is invalid");
    }

    @Test
    void validateRequiredFields_shouldPassForValidEnterprise() {
        Enterprise enterprise = createValidEnterprise();
        assertThatNoException().isThrownBy(() -> service.validateRequiredFields(enterprise));
    }

    @Test
    void validateRequiredFields_shouldThrowWhenNameMissing() {
        Enterprise enterprise = createValidEnterprise();
        enterprise.setTenDoanhNghiep(null);
        assertThatThrownBy(() -> service.validateRequiredFields(enterprise))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Enterprise name is required");
    }

    @Test
    void validateRequiredFields_shouldThrowWhenTaxCodeMissing() {
        Enterprise enterprise = createValidEnterprise();
        enterprise.setMaSoThue(null);
        assertThatThrownBy(() -> service.validateRequiredFields(enterprise))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Tax code is required");
    }

    @Test
    void validateRequiredFields_shouldThrowWhenAddressMissing() {
        Enterprise enterprise = createValidEnterprise();
        enterprise.setDiaChi("  ");
        assertThatThrownBy(() -> service.validateRequiredFields(enterprise))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Address is required");
    }

    @Test
    void validateRequiredFields_shouldThrowWhenProvinceMissing() {
        Enterprise enterprise = createValidEnterprise();
        enterprise.setMaTinh(null);
        assertThatThrownBy(() -> service.validateRequiredFields(enterprise))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Province code is required");
    }

    @Test
    void validateRequiredFields_shouldThrowWhenWardMissing() {
        Enterprise enterprise = createValidEnterprise();
        enterprise.setMaXa("");
        assertThatThrownBy(() -> service.validateRequiredFields(enterprise))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Ward code is required");
    }

    private Enterprise createValidEnterprise() {
        return new Enterprise(
                new EnterpriseUuid(java.util.UUID.randomUUID()),
                "Test Enterprise",
                "DN",
                "123 Test St",
                "01",
                "001",
                null,
                null,
                null,
                new Email("test@example.com"),
                null,
                new TaxCode("1234567890"),
                "A",
                "011"
        );
    }
}
