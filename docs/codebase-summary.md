# Codebase Summary

## Overview

This is a new Java Spring Boot project. The following describes the intended codebase structure following Clean Architecture principles.

## Layer Structure

```
src/main/java/com/example/hdld/
  HdldApplication.java
  domain/
    entity/
      Enterprise.java
      LaborContract.java
      EmployeeInfo.java
      ContractInfo.java
      Attachment.java
      Transaction.java
      Province.java
      Ward.java
      BusinessSector.java
      Industry.java
      EnterpriseType.java
    valueobject/
      EnterpriseUuid.java
      ContractUuid.java
      TransactionId.java
      Email.java
      TaxCode.java
    exception/
      DomainException.java
      ValidationException.java
      NotFoundException.java
    service/
      EnterpriseDomainService.java
      ContractDomainService.java
    repository/
      EnterpriseRepository.java
      LaborContractRepository.java
      AttachmentRepository.java
      TransactionRepository.java
      ProvinceRepository.java
      WardRepository.java
      BusinessSectorRepository.java
      IndustryRepository.java
      EnterpriseTypeRepository.java
  application/
    dto/
      request/
        LoginRequest.java
        ChangePasswordRequest.java
        CreateEnterpriseRequest.java
        UpdateEnterpriseRequest.java
        CreateContractRequest.java
        BulkCreateContractRequest.java
        UpdateContractRequest.java
        UploadAttachmentRequest.java
        DeleteAttachmentRequest.java
        CheckTransactionRequest.java
        GetContractRequest.java
      response/
        ApiResponse.java
        LoginResponse.java
        EnterpriseResponse.java
        ContractResponse.java
        TransactionStatusResponse.java
        AttachmentResponse.java
        ProvinceResponse.java
        WardResponse.java
        BusinessSectorResponse.java
        IndustryResponse.java
        EnterpriseTypeResponse.java
    mapper/
      EnterpriseMapper.java
      ContractMapper.java
      AttachmentMapper.java
      ReferenceDataMapper.java
    usecase/
      AuthenticateUseCase.java
      ChangePasswordUseCase.java
      CreateEnterpriseUseCase.java
      UpdateEnterpriseUseCase.java
      CreateContractUseCase.java
      BulkCreateContractUseCase.java
      UpdateContractUseCase.java
      UploadAttachmentUseCase.java
      DeleteAttachmentUseCase.java
      CheckTransactionUseCase.java
      GetContractUseCase.java
      ListProvincesUseCase.java
      ListWardsUseCase.java
      ListBusinessSectorsUseCase.java
      ListIndustriesUseCase.java
      ListEnterpriseTypesUseCase.java
    port/
      TokenPort.java
      FileStoragePort.java
      PasswordEncoderPort.java
  infrastructure/
    config/
      SecurityConfig.java
      JwtConfig.java
      OpenApiConfig.java
      StorageConfig.java
    security/
      JwtTokenProvider.java
      JwtAuthenticationFilter.java
      CustomUserDetailsService.java
    persistence/
      json/
        JsonEnterpriseRepository.java
        JsonLaborContractRepository.java
        JsonAttachmentRepository.java
        JsonTransactionRepository.java
        JsonProvinceRepository.java
        JsonWardRepository.java
        JsonBusinessSectorRepository.java
        JsonIndustryRepository.java
        JsonEnterpriseTypeRepository.java
      serializer/
        JsonSerializer.java
    web/
      RestExceptionHandler.java
      GlobalControllerAdvice.java
  presentation/
    controller/
      AuthController.java
      EnterpriseController.java
      LaborContractController.java
      AttachmentController.java
      TransactionController.java
      ReferenceDataController.java
    assembler/
      ApiResponseAssembler.java
src/main/resources/
  application.properties
  application-dev.properties
  static/
  templates/
src/test/java/
  unit/
  integration/
```

## Key Patterns

- **Repository pattern**: Domain defines interfaces; infrastructure implements JSON file adapters
- **Use Case pattern**: Each API maps to one use case class
- **DTO mapping**: MapStruct or manual mappers in application layer
- **Port/Adapter**: TokenPort, FileStoragePort, PasswordEncoderPort abstract infrastructure
- **Factory**: Domain services create complex aggregates
- **Strategy**: Contract update supports 5 change types via strategy pattern

## Unresolved Questions
- Will MapStruct be used or manual mappers?
- Should Lombok be allowed?
