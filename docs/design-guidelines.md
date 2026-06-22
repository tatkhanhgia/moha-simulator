# Design Guidelines

## RESTful Conventions

- All endpoints under `/hdld/*` per platform spec
- POST for create/update/delete actions (spec uses POST for most mutations)
- GET for reference data queries
- HTTPS only
- UTF-8 JSON request/response bodies

## Request/Response Format

### Standard Response Wrapper

Every response must include:

```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công"
}
```

Additional fields appended as needed:
- `token` (login)
- `Doanhnghiep_uuid` / `doanhnghiep_uuid` (enterprise)
- `transaction_id` (async operations)
- `hopdong_uuid` / `mahopdong` (contracts)
- `uuid_file` / `ma_giao_dich` (attachments)
- `data` (arrays or objects)

### Request Headers

| Header | Value |
|--------|-------|
| Content-Type | application/json |
| Authorization | Bearer <token> |

## Error Handling

Return exact error codes from spec:

| Code | HTTP Status | When |
|------|-------------|------|
| E00 | 200 | Success |
| E01 | 401 | Invalid or expired token |
| E02 | 400 | Data format invalid |
| E03 | 400 | Missing required fields |
| E05 | 409 | Data already exists |
| E06 | 500 | Platform/system error |

Error response example:

```json
{
  "status": 400,
  "error_code": "E03",
  "message": "Dữ liệu không đúng định dạng. Một hoặc nhiều trường dữ liệu bắt buộc bị thiếu (ten_doanh_nghiep, ma_so_thue)"
}
```

## Swagger Annotations Standards

Use SpringDoc annotations on controllers and DTOs:

```java
@Tag(name = "Enterprise", description = "Enterprise management APIs")
@RestController
public class EnterpriseController {

    @Operation(summary = "Create enterprise")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping("/hdld/ThemMoiDoanhNghiep")
    public ResponseEntity<ApiResponse<EnterpriseResponse>> create(
        @RequestBody @Valid CreateEnterpriseRequest request
    ) { ... }
}
```

On DTO fields:

```java
@Schema(description = "Enterprise name", example = "Công ty TNHH ABC")
@NotBlank
@Size(max = 250)
private String tenDoanhNghiep;
```

## Field Naming

- DTO fields use camelCase in Java
- JSON serialization uses snake_case to match platform spec
- Configure Jackson: `PropertyNamingStrategies.SNAKE_CASE`

## Folder-Based Storage Design

### Structure

```
{storage.base-path}/
  enterprises/{uuid}.json
  contracts/{uuid}.json
  attachments/
    meta/{uuid}.json
    files/{uuid}.pdf
  transactions/{transactionId}.json
  provinces/{id}.json
  wards/{id}.json
  business-sectors/{id}.json
  industries/{id}.json
  enterprise-types/{id}.json
```

### Rules

- One file per record
- File name is the record's unique identifier
- Attachment metadata stored separately from binary file
- Read all files in folder to list; filter in memory
- Write atomically: write to temp file, then rename

## Bulk Create Constraints

- Max 100 contracts per request
- Validate count before processing
- Return single transaction_id for the batch
- If any item fails validation, reject entire batch (atomic)

## Attachment Constraints

- Accept Base64-encoded PDF only
- Max decoded size 5MB
- Store decoded bytes as file; keep metadata in JSON
- Generate uuid_file on successful save

## Unresolved Questions
- Should contract update support partial success or all-or-nothing?
- Is there a preferred library for Base64 decoding (java.util vs Apache Commons)?
