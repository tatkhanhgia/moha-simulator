package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UploadAttachmentRequest;
import com.example.hdld.application.dto.response.UploadFileResponse;
import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.entity.Attachment;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.valueobject.ContractUuid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadAttachmentUseCaseTest {

    @Mock
    private AttachmentRepository attachmentRepository;
    @Mock
    private LaborContractRepository contractRepository;
    @Mock
    private FileStoragePort fileStoragePort;
    @Mock
    private TransactionRepository transactionRepository;

    private UploadAttachmentUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UploadAttachmentUseCase(attachmentRepository, contractRepository, fileStoragePort, transactionRepository);
    }

    @Test
    void execute_shouldUploadValidBase64() {
        String contractUuid = UUID.randomUUID().toString();
        when(contractRepository.findById(new ContractUuid(contractUuid)))
                .thenReturn(Optional.of(new com.example.hdld.domain.entity.LaborContract()));

        String base64 = java.util.Base64.getEncoder().encodeToString("PDF content".getBytes());
        UploadAttachmentRequest request = new UploadAttachmentRequest(contractUuid, base64);

        when(fileStoragePort.store(any(), any())).thenReturn("/path/to/file.pdf");
        when(attachmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UploadFileResponse response = useCase.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.getMaGiaoDich()).isNotBlank();
        assertThat(response.getUuidFile()).isNotBlank();
    }

    @Test
    void execute_shouldThrowNotFoundWhenContractMissing() {
        String contractUuid = UUID.randomUUID().toString();
        when(contractRepository.findById(new ContractUuid(contractUuid)))
                .thenReturn(Optional.empty());

        String base64 = java.util.Base64.getEncoder().encodeToString("x".getBytes());
        UploadAttachmentRequest request = new UploadAttachmentRequest(contractUuid, base64);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Contract not found");
    }

    @Test
    void execute_shouldThrowValidationForInvalidBase64() {
        String contractUuid = UUID.randomUUID().toString();
        when(contractRepository.findById(new ContractUuid(contractUuid)))
                .thenReturn(Optional.of(new com.example.hdld.domain.entity.LaborContract()));

        UploadAttachmentRequest request = new UploadAttachmentRequest(contractUuid, "!!!not-base64!!!");

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Invalid Base64 content");
    }

    @Test
    void execute_shouldThrowValidationWhenSizeExceeds5MB() {
        String contractUuid = UUID.randomUUID().toString();
        when(contractRepository.findById(new ContractUuid(contractUuid)))
                .thenReturn(Optional.of(new com.example.hdld.domain.entity.LaborContract()));

        byte[] oversized = new byte[5 * 1024 * 1024 + 1];
        String base64 = java.util.Base64.getEncoder().encodeToString(oversized);
        UploadAttachmentRequest request = new UploadAttachmentRequest(contractUuid, base64);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("File size exceeds 5MB limit");
    }

    @Test
    void execute_shouldAcceptExactly5MB() {
        String contractUuid = UUID.randomUUID().toString();
        when(contractRepository.findById(new ContractUuid(contractUuid)))
                .thenReturn(Optional.of(new com.example.hdld.domain.entity.LaborContract()));

        byte[] exactly5MB = new byte[5 * 1024 * 1024];
        String base64 = java.util.Base64.getEncoder().encodeToString(exactly5MB);
        UploadAttachmentRequest request = new UploadAttachmentRequest(contractUuid, base64);

        when(fileStoragePort.store(any(), any())).thenReturn("/path/to/file.pdf");
        when(attachmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UploadFileResponse response = useCase.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.getMaGiaoDich()).isNotBlank();
        assertThat(response.getUuidFile()).isNotBlank();
    }
}
