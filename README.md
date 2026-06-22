# National Electronic Labor Contract Platform Integration Service

Java Spring Boot service integrating with Vietnam's National Electronic Labor Contract Platform (Nen tang HDLD dien tu quoc gia). Exposes 16 REST APIs for enterprise and labor contract synchronization.

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.2.x |
| Security | Spring Security + JWT |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Build | Maven |
| Data (temp) | Folder-based JSON storage |

## Key Features

- JWT authentication (/hdld/login)
- Enterprise CRUD (/hdld/ThemMoiDoanhNghiep, /hdld/CapNhatDoanhNghiep)
- Labor contract single & bulk creation (/hdld/ThemMoiHopDongLaoDong, /hdld/ThemMoiTheoLoHopDongLaoDong)
- Contract updates with 5 change types (/hdld/CapNhatHopDongLaoDong)
- Attachment upload/delete (Base64 PDF, max 5MB)
- Transaction status checking (/hdld/KiemTraTrangThaiGiaoDich)
- Reference data APIs (provinces, wards, business sectors, industries, enterprise types)

## Quick Start

### Prerequisites
- JDK 17+
- Maven 3.9+

### Run
```bash
mvn clean install
mvn spring-boot:run
```

### API Documentation
After startup, open: `http://localhost:8080/swagger-ui.html`

## Project Structure

Follows Clean Architecture:
```
src/main/java/com/example/hdld/
  domain/        -- Entities, value objects, domain services
  application/   -- Use cases, DTOs, mappers, ports
  infrastructure/-- Adapters: JWT, file storage, web clients
  presentation/  -- REST controllers, exception handlers
```

## Configuration

Temporary folder-based storage path (until database is deployed):
```properties
storage.base-path=./data
```

## Unresolved Questions
- Will the target database be PostgreSQL or MySQL?
- Is there an existing enterprise identity provider for user provisioning?
