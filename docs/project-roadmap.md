# Project Roadmap

## Phase 1: Project Setup + Domain Layer

- Initialize Spring Boot 3.2.x project with Maven
- Configure Java 17 toolchain
- Add dependencies: Spring Web, Spring Security, SpringDoc OpenAPI, Jackson, Jakarta Validation
- Create Clean Architecture package structure
- Implement domain entities: Enterprise, LaborContract, EmployeeInfo, ContractInfo, Attachment, Transaction, Province, Ward, BusinessSector, Industry, EnterpriseType
- Implement value objects: EnterpriseUuid, ContractUuid, TransactionId, Email, TaxCode
- Define domain exceptions
- Define repository interfaces (ports)
- Status: Not started

## Phase 2: Application Layer + DTOs/Use Cases

- Create request/response DTOs for all 16 APIs
- Create mappers (manual or MapStruct)
- Implement use cases:
  - AuthenticateUseCase, ChangePasswordUseCase
  - CreateEnterpriseUseCase, UpdateEnterpriseUseCase
  - CreateContractUseCase, BulkCreateContractUseCase, UpdateContractUseCase
  - UploadAttachmentUseCase, DeleteAttachmentUseCase
  - CheckTransactionUseCase, GetContractUseCase
  - ListProvincesUseCase, ListWardsUseCase, ListBusinessSectorsUseCase, ListIndustriesUseCase, ListEnterpriseTypesUseCase
- Implement domain services for validation
- Status: Not started
- Depends on: Phase 1

## Phase 3: Infrastructure (Persistence, JWT, Swagger)

- Implement JSON file repositories
- Implement file storage adapter for attachments
- Implement JwtTokenProvider and JwtAuthenticationFilter
- Configure Spring Security (permit login, secure others)
- Configure SpringDoc OpenAPI with bearer auth
- Add application.properties profiles (dev, default)
- Status: Not started
- Depends on: Phase 2

## Phase 4: Presentation Layer (Controllers)

- Implement controllers:
  - AuthController (/hdld/login, /hdld/QuenMatKhau)
  - EnterpriseController (/hdld/ThemMoiDoanhNghiep, /hdld/CapNhatDoanhNghiep)
  - LaborContractController (/hdld/ThemMoiHopDongLaoDong, /hdld/ThemMoiTheoLoHopDongLaoDong, /hdld/CapNhatHopDongLaoDong)
  - AttachmentController (/hdld/UploadFileHopDong, /hdld/XoaFileHopDong)
  - TransactionController (/hdld/KiemTraTrangThaiGiaoDich)
  - ReferenceDataController (/hdld/tinhthanhpho, /hdld/xaphuong, /hdld/linhvuckinhdoanh, /hdld/nganhnghe, /hdld/danhmuc)
- Implement global exception handler returning spec-compliant error format
- Implement ApiResponseAssembler
- Status: Not started
- Depends on: Phase 3

## Phase 5: Testing + Integration

- Unit tests for domain services and use cases
- Integration tests for controllers with MockMvc
- Validate all 16 endpoints against spec samples
- Validate error codes E01-E06
- Validate JWT flow
- Validate 5MB upload limit
- Validate bulk max 100 contracts
- Status: Not started
- Depends on: Phase 4

## Milestones

| Milestone | Target | Success Criteria |
|-----------|--------|------------------|
| M1 | End of Phase 1 | Project compiles, domain layer complete |
| M2 | End of Phase 3 | All use cases wired, Swagger accessible, JWT works |
| M3 | End of Phase 4 | All 16 endpoints callable via Postman |
| M4 | End of Phase 5 | All tests pass, spec compliance verified |

## Unresolved Questions
- Target completion date for each phase?
- Will there be a separate QA environment?
