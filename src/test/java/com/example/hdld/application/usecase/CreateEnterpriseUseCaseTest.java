package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.CreateEnterpriseRequest;
import com.example.hdld.application.dto.response.CreateEnterpriseResponse;
import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.service.EnterpriseDomainService;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEnterpriseUseCaseTest {

    @Mock
    private EnterpriseRepository enterpriseRepository;

    private EnterpriseDomainService domainService;
    private CreateEnterpriseUseCase useCase;

    @BeforeEach
    void setUp() {
        domainService = new EnterpriseDomainService(enterpriseRepository);
        useCase = new CreateEnterpriseUseCase(enterpriseRepository, domainService);
    }

    @Test
    void execute_shouldCreateAndReturnEnterprise() {
        CreateEnterpriseRequest request = new CreateEnterpriseRequest();
        request.setTenDoanhNghiep("Test Corp");
        request.setLoaiHinhDoanhNghiep("DN");
        request.setDiaChi("123 Main St");
        request.setMaTinh("01");
        request.setMaXa("001");
        request.setMaSoThue("1234567890");
        request.setMaLinhVuc("A");
        request.setMaNganhNghe("011");
        request.setEmail("test@example.com");

        ArgumentCaptor<Enterprise> captor = ArgumentCaptor.forClass(Enterprise.class);
        when(enterpriseRepository.save(captor.capture())).thenAnswer(inv -> {
            Enterprise e = inv.getArgument(0);
            e.setUuid(new EnterpriseUuid(java.util.UUID.randomUUID()));
            return e;
        });

        CreateEnterpriseResponse response = useCase.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.getDoanhnghiepUuid()).isNotBlank();
        assertThat(captor.getValue().getTenDoanhNghiep()).isEqualTo("Test Corp");
    }

    @Test
    void execute_shouldThrowWhenNameMissing() {
        CreateEnterpriseRequest request = new CreateEnterpriseRequest();
        request.setTenDoanhNghiep("  ");
        request.setMaSoThue("1234567890");
        request.setDiaChi("123 Main St");
        request.setMaTinh("01");
        request.setMaXa("001");
        request.setMaLinhVuc("A");
        request.setMaNganhNghe("011");

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Enterprise name is required");
    }

    @Test
    void execute_shouldThrowWhenEmailInvalid() {
        CreateEnterpriseRequest request = new CreateEnterpriseRequest();
        request.setTenDoanhNghiep("Test Corp");
        request.setLoaiHinhDoanhNghiep("DN");
        request.setDiaChi("123 Main St");
        request.setMaTinh("01");
        request.setMaXa("001");
        request.setMaSoThue("1234567890");
        request.setMaLinhVuc("A");
        request.setMaNganhNghe("011");
        request.setEmail("invalid-email");

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Email format is invalid");
    }
}
