# Code Standards

## General

- Java 17 with preview features disabled
- Spring Boot 3.2.x
- Maven build
- UTF-8 source encoding
- No Lombok (prefer explicit code for clarity)

## Naming Conventions

| Element | Convention | Example |
|---------|------------|---------|
| Packages | lowercase, no underscores | `com.example.hdld.domain.entity` |
| Classes | PascalCase | `LaborContractController` |
| Interfaces | PascalCase, no prefix | `TokenPort` |
| Methods | camelCase | `createEnterprise` |
| Variables | camelCase | `enterpriseUuid` |
| Constants | UPPER_SNAKE_CASE | `MAX_BULK_CONTRACTS` |
| DTOs | suffix `Request` / `Response` | `CreateContractRequest` |
| Use cases | suffix `UseCase` | `CreateContractUseCase` |

## Clean Architecture Rules

1. **Domain has no dependencies** on other layers or frameworks
2. **Application depends only on Domain** and standard library
3. **Infrastructure depends on Application and Domain**
4. **Presentation depends on Application** (never on Infrastructure directly)
5. Dependencies point inward; use ports (interfaces) to invert dependencies

## Package Rules

- `domain` must not import `application`, `infrastructure`, or `presentation`
- `application` must not import `infrastructure` or `presentation`
- `infrastructure` may import `application` and `domain`
- `presentation` may import `application` and `domain`

## GoF Pattern Usage

Apply patterns only when they solve a real problem:

| Pattern | Where | Why |
|---------|-------|-----|
| Repository | Domain + Infrastructure | Abstract persistence |
| Factory | Domain service | Create complex aggregates |
| Strategy | Contract update use case | 5 change types |
| Adapter | Infrastructure | External APIs, file storage |
| Singleton | Config classes | Spring-managed beans only |

## Error Handling

- Domain exceptions extend `RuntimeException`
- Use `@ControllerAdvice` in presentation for HTTP mapping
- Never expose stack traces in API responses
- Always return `status`, `error_code`, `message` per spec

## Validation

- Bean Validation (Jakarta) on request DTOs
- Domain-level validation in entities and domain services
- Fail fast: validate at application boundary

## File Size

- Keep classes under 200 lines
- Split large use cases into helper classes
- One public class per file

## Unresolved Questions
- Should we adopt SpotBugs or Checkstyle?
- Preferred test framework: JUnit 5 + AssertJ + Mockito?
