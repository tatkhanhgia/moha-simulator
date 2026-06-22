# Product Development Requirements

## Project Goals

Build a Java Spring Boot integration service that synchronizes enterprise and labor contract data with Vietnam's National Electronic Labor Contract Platform. The service acts as a bridge between eContract providers and the national platform, exposing 16 standardized REST APIs.

## Scope

### In Scope
- Authentication: login, password change
- Enterprise management: create, update
- Labor contract management: single create, bulk create (max 100 per call), update
- Attachment handling: upload Base64 PDF (max 5MB), delete
- Transaction status checking
- Contract information retrieval
- Reference data: provinces, wards, business sectors, industries, enterprise types

### Out of Scope
- UI/frontend
- UI/frontend
- Real-time webhook push from national platform
- Multi-tenant isolation beyond JWT token scope

## Functional Requirements

| ID | Requirement | API |
|----|-------------|-----|
| FR-01 | Authenticate with username/password and receive JWT | POST /hdld/login |
| FR-02 | Change password with old/new password | POST /hdld/QuenMatKhau |
| FR-03 | Create enterprise with validated fields | POST /hdld/ThemMoiDoanhNghiep |
| FR-04 | Update enterprise by UUID | POST /hdld/CapNhatDoanhNghiep |
| FR-05 | Create single labor contract | POST /hdld/ThemMoiHopDongLaoDong |
| FR-06 | Bulk create up to 100 labor contracts | POST /hdld/ThemMoiTheoLoHopDongLaoDong |
| FR-07 | Update contract with 5 change types | POST /hdld/CapNhatHopDongLaoDong |
| FR-08 | Upload Base64 PDF attachment (max 5MB) | POST /hdld/UploadFileHopDong |
| FR-09 | Delete attachment by file UUID | POST /hdld/XoaFileHopDong |
| FR-10 | Check async transaction status | POST /hdld/KiemTraTrangThaiGiaoDich |
| FR-11 | Retrieve contract info by UUID | POST /hdld/LayThongTinHopDong |
| FR-12 | List provinces | GET /hdld/tinhthanhpho |
| FR-13 | List wards with pagination | GET /hdld/xaphuong |
| FR-14 | List business sectors | GET /hdld/linhvuckinhdoanh |
| FR-15 | List industries | GET /hdld/nganhnghe |
| FR-16 | List enterprise types | GET /hdld/danhmuc?loai=loai-hinh-doanh-nghiep |

## Non-Functional Requirements

| ID | Requirement |
|----|-------------|
| NFR-01 | Java 17, Spring Boot 3.2.x |
| NFR-02 | RESTful JSON over HTTPS |
| NFR-03 | JWT Bearer token authorization |
| NFR-04 | Swagger/OpenAPI documentation |
| NFR-05 | Clean Architecture with strict layer boundaries |
| NFR-06 | SOLID compliance; GoF patterns applied purposefully |
| NFR-07 | DRY, KISS, YAGNI |
| NFR-08 | H2 database (file-based) for rapid dev; PostgreSQL migration ready |
| NFR-09 | UTF-8 encoding |
| NFR-10 | Max upload 5MB |

## Error Codes (from spec)

| Code | Meaning |
|------|---------|
| E00 | Success |
| E01 | Invalid or expired token |
| E02 | Data format invalid |
| E03 | Missing required fields |
| E05 | Data already exists |
| E06 | Platform/system error |

## Acceptance Criteria

- All 16 endpoints return correct status, error_code, message structure
- JWT tokens are validated on every protected endpoint
- Request/response formats match spec samples exactly
- Bulk create enforces max 100 contracts per request
- Upload rejects files larger than 5MB
- All reference data endpoints return arrays with correct field names

## Clarifications
- **Role**: This project implements the server-side APIs (platform/provider), not a client calling external APIs.
- **Database**: H2 (file-based) for rapid development; easy migration path to PostgreSQL.
- **Sandbox**: None available.
- **CI/CD**: Not required; Docker deployment supported.

## Unresolved Questions
- Are there rate limits expected from consumers?
- Will enterprise user accounts be provisioned manually or via an admin API?
