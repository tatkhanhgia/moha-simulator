# HDLD Platform API — Tài liệu tham khảo đầy đủ

> **Ngày cập nhật**: 2026-06-26
> **Project**: HDLD Platform Simulator (mô phỏng Nền tảng HĐLĐ điện tử quốc gia)
> **Spring Boot**: 3.2.12 | **Java**: 17
> **Chuẩn contract**: khớp tài liệu chính thức `Huong-dan-tich-hop-Nen-tang-HDLD.pdf` (xem `docs/pdf_extract.txt`).

---

## 📌 Quy ước contract (đọc trước)

Simulator này tuân theo đúng contract của nền tảng thật:

1. **Không có wrapper `data`.** Mọi field nằm ở **root** của response (trừ vài endpoint có `data` riêng: kiểm tra giao dịch, lấy thông tin hợp đồng, danh sách xã/phường).
2. **`status` là SỐ** (`200`, `203`) — không phải chuỗi `"success"`.
3. **Field name là snake_case tiếng Việt**: `doanhnghiep_uuid`, `ma_giao_dich`, `uuid_hop_dong`, `hopdong_uuid`, `mahopdong`, `uuid_file`...
4. **Ngày tháng**: input dạng **`ddmmyyyy`** (vd `"27041986"`); output dạng **`dd/MM/yyyy`** (vd `"27/04/1986"`). Chuỗi rỗng `""` được coi là `null`.
5. **Mô hình bất đồng bộ**: các API ghi (tạo/cập nhật/upload/xóa) trả về `transaction_id`/`ma_giao_dich`; client gọi `KiemTraTrangThaiGiaoDich` để lấy kết quả.
6. **Mã lỗi**: `E00` thành công · `E01` token · `E02` sai định dạng · `E03` thiếu trường bắt buộc · `E05` đã tồn tại · `E06` lỗi hệ thống.

---

## 🌐 Đường dẫn đang chạy

| Service | URL |
|---------|-----|
| **Local** | http://localhost:8080 |
| **Ngrok Public** | https://yuko-unremonstrated-noah.ngrok-free.dev |
| **Swagger UI** | https://yuko-unremonstrated-noah.ngrok-free.dev/swagger-ui.html |
| **API Docs (JSON)** | https://yuko-unremonstrated-noah.ngrok-free.dev/api-docs |
| **H2 Console** | https://yuko-unremonstrated-noah.ngrok-free.dev/h2-console |
| **Ngrok Dashboard** | http://127.0.0.1:4040 |

---

## 1. Authentication — Đăng nhập & Lấy JWT Token

### 1.1. Login

**Request:**
```
POST /hdld/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}
```

**Response (200 OK)** — `token` ở **root**, không có wrapper `data`, không có `expires_in`:
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Response (401 Unauthorized):**
```json
{
  "status": 401,
  "error_code": "E01",
  "message": "Invalid username or password"
}
```

> Token hết hạn sau 24h. Hash mật khẩu: BCrypt.

#### Header Authorization
Mọi request (trừ `/hdld/login`) phải gửi token:
```
Authorization: Bearer <token_cua_ban>
```

### 1.2. Đổi mật khẩu

```
POST /hdld/QuenMatKhau
Content-Type: application/json
Authorization: Bearer <token>

{
  "oldPassword": "matKhauCu",
  "newPassword": "matKhauMoi"
}
```

> ⚠️ Endpoint này dùng field camelCase `oldPassword`/`newPassword` (không phải snake_case).

**Response** — shape riêng `{status, token, msg}` (dùng `msg`, không có `error_code`):
```json
{
  "status": 200,
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "msg": "Thành công"
}
```

---

## 2. Danh sách API đầy đủ

> Tất cả API bên dưới (trừ `/hdld/login`) đều yêu cầu `Authorization: Bearer <token>`.

### 2.1. Enterprise — Doanh nghiệp

**Controller**: `EnterpriseController.java`

#### `POST /hdld/ThemMoiDoanhNghiep` — Thêm mới doanh nghiệp

**Request:**
```json
{
  "ten_doanh_nghiep": "Cong ty TNHH ABC",
  "loai_hinh_doanh_nghiep": "TNHH",
  "dia_chi": "123 Duong Le Loi",
  "ma_tinh": "HNI",
  "ma_xa": "DD",
  "nguoi_dai_dien": "Nguyen Van A",
  "dien_thoai": "0909123456",
  "fax": "",
  "email": "info@abc.com",
  "website": "",
  "ma_so_thue": "1234567890",
  "ma_linh_vuc": "A",
  "ma_nganh_nghe": "0111"
}
```

**Response** — UUID ở root, key `Doanhnghiep_uuid` (**chữ D hoa**):
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "Doanhnghiep_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

#### `POST /hdld/CapNhatDoanhNghiep` — Cập nhật doanh nghiệp

**Request** — định danh bằng `doanhnghiep_uuid`:
```json
{
  "doanhnghiep_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "ten_doanh_nghiep": "Cong ty TNHH ABC (Cap nhat)",
  "loai_hinh_doanh_nghiep": "TNHH",
  "dia_chi": "456 Duong Khac",
  "ma_tinh": "HNI",
  "ma_xa": "MYDINH",
  "nguoi_dai_dien": "Tran Thi B",
  "dien_thoai": "0909987654",
  "email": "info@abc.com",
  "ma_so_thue": "1234567890",
  "ma_linh_vuc": "B",
  "ma_nganh_nghe": "0712"
}
```

**Response** — key `doanhnghiep_uuid` (**chữ d thường**):
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "doanhnghiep_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

---

### 2.2. Labor Contract — Hợp đồng lao động

**Controller**: `LaborContractController.java`

#### `POST /hdld/ThemMoiHopDongLaoDong` — Thêm mới HĐLĐ

**Request** — `doanhnghiep_uuid` + 2 object lồng `thong_tin_nld` & `thong_tin_hop_dong`; ngày dạng `ddmmyyyy`:
```json
{
  "doanhnghiep_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "thong_tin_nld": {
    "ho_ten": "Tran Xuan Truong",
    "ma_so_bhxh": "111111",
    "ngay_sinh": "27041986",
    "gioi_tinh": "Nam",
    "ma_dinh_danh": "033086002518",
    "email": "truong@example.com",
    "dien_thoai": "0913152869"
  },
  "thong_tin_hop_dong": {
    "cap_bac": "Cap 3",
    "vi_tri_lam_viec": "Lap trinh vien",
    "muc_luong": "20000000",
    "phu_cap_chuc_vu": "200000",
    "phu_cap_tham_nien": "15",
    "tham_nien_nghe": "2",
    "phu_cap_luong": "200000",
    "cac_khoan_bo_sung": "700000",
    "doc_hai_tu_ngay": "01012020",
    "doc_hai_den_ngay": "01012026",
    "loai_hop_dong": "2",
    "ma_so_hop_dong": "HD00088",
    "loai_bao_cao": "1",
    "uuid_hop_dong": "",
    "ngay_hieu_luc": "01012024",
    "ngay_het_hieu_luc": "",
    "thoi_gian_bat_dau_bhxh": "01012010",
    "thoi_gian_ket_thuc_bhxh": "01012025",
    "ghi_chu": "Tao moi hop dong",
    "phuong_thuc_ky_ket": "1"
  }
}
```

**Response** — bất đồng bộ, chỉ trả `transaction_id`; `hopdong_uuid`/`mahopdong` = `null`:
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Hệ thống đã ghi nhận dữ liệu, vui lòng kiểm tra kết quả ở api kiểm tra transaction_id",
  "transaction_id": "insert_20260626071354_5wrSp1",
  "hopdong_uuid": null,
  "mahopdong": null
}
```

> Lấy `hopdong_uuid` thật bằng cách gọi `KiemTraTrangThaiGiaoDich` với `ma_giao_dich = transaction_id` ở trên.

#### `POST /hdld/ThemMoiTheoLoHopDongLaoDong` — Thêm mới theo lô HĐLĐ

**Request** — danh sách hợp đồng (mỗi phần tử như payload tạo đơn ở trên):
```json
{
  "contracts": [
    { "doanhnghiep_uuid": "...", "thong_tin_nld": { }, "thong_tin_hop_dong": { } },
    { "doanhnghiep_uuid": "...", "thong_tin_nld": { }, "thong_tin_hop_dong": { } }
  ]
}
```

**Response** — giống tạo đơn nhưng `transaction_id` mang tiền tố `insertlo_`:
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Hệ thống đã ghi nhận dữ liệu, vui lòng kiểm tra kết quả ở api kiểm tra transaction_id",
  "transaction_id": "insertlo_20260626094436_VagbCR",
  "hopdong_uuid": null,
  "mahopdong": null
}
```

> Lưu ý: ở simulator, một lô lưu **1 transaction tổng**; khi poll trả về 1 dòng `data[]` tóm tắt.

#### `POST /hdld/CapNhatHopDongLaoDong` — Cập nhật HĐLĐ

**Request** — yêu cầu thay đổi có kiểu: `uuid_hop_dong` + `loai_thay_doi` (1–5) + `thong_tin_thay_doi`:

| `loai_thay_doi` | Ý nghĩa | Field chính trong `thong_tin_thay_doi` |
|---|---|---|
| 1 | Thay đổi mức lương | `muc_luong`, `phu_cap_*`, `ngay_hieu_luc`, `ly_do` |
| 2 | Gia hạn hợp đồng | `ngay_ket_thuc_moi`, `ly_do` |
| 3 | Chấm dứt hợp đồng | `ngay_cham_dut_hop_dong`, `ly_do` |
| 4 | Thay đổi vị trí làm việc | `vi_tri_lam_viec`, `cap_bac`, `ngay_app_dung_dia_diem_moi`, `ly_do` |
| 5 | Cập nhật thông tin BHXH | `ma_so_bhxh`, `thoi_gian_bat_dau_bhxh`, `thoi_gian_ket_thuc_bhxh`, `ly_do` |

```json
{
  "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "loai_thay_doi": 1,
  "thong_tin_thay_doi": {
    "muc_luong": 25000000,
    "ngay_hieu_luc": "10062025",
    "phu_cap_chuc_vu": "11",
    "phu_cap_tham_nien": "11",
    "tham_nien_nghe": "11",
    "phu_cap_luong": "",
    "cac_khoan_bo_sung": "",
    "ly_do": ""
  }
}
```

**Response:**
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "hopdong_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "ma_giao_dich": "update_20260626075732_gcSbIW"
}
```

#### `POST /hdld/ThongTinHopDong` — Lấy thông tin HĐLĐ

> ⚠️ Path đổi từ `/hdld/LayThongTinHopDong` → **`/hdld/ThongTinHopDong`**.

**Request:**
```json
{
  "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

**Response** — chi tiết trong object **`data` phẳng** (gộp NLĐ + hợp đồng), ngày `dd/MM/yyyy`:
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "ma_giao_dich": "checkinfo_20260626094254_pjPmZz",
  "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "data": {
    "ho_ten": "Tran Xuan Truong",
    "ma_so_bhxh": "111111",
    "ngay_sinh": "27/04/1986",
    "gioi_tinh": "Nam",
    "ma_dinh_danh": "033086002518",
    "email": "truong@example.com",
    "dien_thoai": "0913152869",
    "cap_bac": "Cap 3",
    "vi_tri_lam_viec": "Lap trinh vien",
    "muc_luong": 20000000,
    "ma_so_hop_dong": "HD00088",
    "loai_hop_dong": "2",
    "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
    "ngay_hieu_luc": "01/01/2024",
    "thoi_gian_bat_dau_bhxh": "01/01/2010",
    "thoi_gian_ket_thuc_bhxh": "01/01/2025",
    "phuong_thuc_ky_ket": "1"
  }
}
```

---

### 2.3. Attachment — File đính kèm hợp đồng

**Controller**: `AttachmentController.java`

#### `POST /hdld/UploadFileHopDongLaoDong` — Upload file

> ⚠️ Path đổi từ `/hdld/UploadFileHopDong` → **`/hdld/UploadFileHopDongLaoDong`**.

**Request** — `uuid_hop_dong` + `file` (PDF base64, ≤ 5MB):
```json
{
  "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "file": "JVBERi0xLjQKJdPr6eEK..."
}
```

**Response:**
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "ma_giao_dich": "upload_20260626091503_e77N1F",
  "uuid_file": "f47ac10b-58cc-4372-a567-0e02b2c3d479"
}
```

#### `POST /hdld/XoaFileHopDong` — Xoá file

**Request** — `uuid_hop_dong` + `uuid_file`:
```json
{
  "uuid_hop_dong": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "uuid_file": "f47ac10b-58cc-4372-a567-0e02b2c3d479"
}
```

**Response** — `status` = **203**:
```json
{
  "status": 203,
  "error_code": "E00",
  "message": "Thành công",
  "ma_giao_dich": "deletefile_20260626092223_h6IM3B",
  "uuid_file": "f47ac10b-58cc-4372-a567-0e02b2c3d479"
}
```

---

### 2.4. Transaction — Giao dịch

**Controller**: `TransactionController.java`

#### `POST /hdld/KiemTraTrangThaiGiaoDich` — Kiểm tra trạng thái giao dịch

**Request** — định danh bằng `ma_giao_dich` (= `transaction_id` nhận từ API ghi):
```json
{
  "ma_giao_dich": "insert_20260626071354_5wrSp1"
}
```

**Response** — kết quả nằm trong mảng `data[]`:
```json
{
  "status": 200,
  "error_code": "E00",
  "message": "Thành công",
  "ma_giao_dich": "checktrans_20260626093028_sOos4A",
  "data": [
    {
      "ma_giao_dich": "insert_20260626071354_5wrSp1",
      "ma_loi": "E00",
      "trang_thai": "Thành công",
      "ket_qua_xu_ly": "Thành công",
      "hopdong_uuid": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
      "ma_so_hop_dong": "HD00088"
    }
  ]
}
```

---

### 2.5. Reference Data — Danh mục dữ liệu

**Controller**: `ReferenceDataController.java`

| Method | Endpoint | Mô tả | Query params | Shape response |
|--------|----------|------|-------------|----------------|
| GET | `/hdld/danh-muc-tinh/list` | DS tỉnh/thành phố | *(không)* | **array** `[{id,ten,ma}]` |
| GET | `/hdld/xa-phuong/paging` | DS xã/phường | `?ma_tinh=&ma=&page_num=&page_size=` | `{total_count, data:[...]}` |
| GET | `/hdld/linhvuckinhdoanh` | DS lĩnh vực KD | *(không)* | **array** `[{id,ma,ten,trang_thai}]` |
| GET | `/hdld/nganhnghe` | DS ngành nghề | *(không)* | **array** (có `linhvuc_kinhdoanh_id/string`) |
| GET | `/hdld/danhmuc` | DS loại hình DN | `?loai=loai-hinh-doanh-nghiep` | **array** (có `ten_tieng_anh`) |

**Ví dụ `/hdld/danh-muc-tinh/list`** — mảng ở root, không bọc envelope:
```json
[
  { "id": 27, "ten": "Sơn La", "ma": "SLA" },
  { "id": 29, "ten": "Lai Châu", "ma": "LCU" }
]
```

**Ví dụ `/hdld/xa-phuong/paging`:**
```json
{
  "total_count": 2,
  "data": [
    { "id": 14, "ma": "MYDINH", "ten": "Mỹ Đình", "ma_tinh": "HNI", "ten_tinh": "TP. Hà Nội", "trang_thai": true, "tinh_id": 15 }
  ]
}
```

> ⚠️ Các bảng danh mục **chưa có seed data** — endpoint trả về mảng rỗng cho tới khi nạp dữ liệu.

---

## 3. Bảo mật

### Cấu hình Security (SecurityConfig.java)

- **CSRF**: Tắt
- **Session**: Stateless (JWT)
- **Công khai (permitAll)**: `POST /hdld/login`, `/h2-console/**`, Swagger/api-docs
- **Yêu cầu xác thực**: `/hdld/**`
- **Filter**: `JwtAuthenticationFilter` (kiểm tra header `Authorization: Bearer <token>`)

### JWT Configuration

| Property | Giá trị | Ghi chú |
|----------|---------|---------|
| `jwt.secret` | `defaultSecretChangeMeInProduction` | ⚠️ Override bằng biến môi trường `JWT_SECRET` |
| `jwt.expiration-ms` | `86400000` | 24 giờ |

> Yêu cầu secret key ≥ 256 bits (32 bytes). Thuật toán HMAC-SHA.

---

## 4. Cấu hình ứng dụng

### application.properties
```properties
server.port=8080
spring.datasource.url=jdbc:h2:file:./data/hdldb;AUTO_SERVER=TRUE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jackson.property-naming-strategy=SNAKE_CASE
spring.jackson.default-property-inclusion=non_null
jwt.secret=${JWT_SECRET:defaultSecretChangeMeInProduction}
jwt.expiration-ms=86400000
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
storage.base-path=./data
```

> **Định dạng ngày** không cấu hình ở đây mà do `JacksonDateConfig.java` xử lý: nhận `ddMMyyyy`, xuất `dd/MM/yyyy`, chuỗi rỗng → `null` (đồng thời `BigDecimal` rỗng → `null`).

### application-prod.properties (Production profile)
```properties
spring.h2.console.enabled=false
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

> Kích hoạt: `--spring.profiles.active=prod` hoặc `SPRING_PROFILES_ACTIVE=prod`.

---

## 5. Cấu trúc Database

### 5.1. Bảng `users`
| Column | Type | Constraints |
|--------|------|------------|
| id | BIGINT | PK, Auto Increment |
| username | VARCHAR(255) | NOT NULL, UNIQUE |
| password_hash | VARCHAR(255) | NOT NULL |
| role | VARCHAR(255) | NOT NULL (VD: `ROLE_USER`) |

### 5.2. Bảng `enterprises`
PK `uuid` (UUID) + các cột `ten_doanh_nghiep`, `loai_hinh_doanh_nghiep`, `dia_chi`, `ma_tinh`, `ma_xa`, `nguoi_dai_dien`, `dien_thoai`, `fax`, `email`, `website`, `ma_so_thue`, `ma_linh_vuc`, `ma_nganh_nghe`, `created_at`, `updated_at`.

### 5.3. Bảng `labor_contracts`
PK `uuid` (UUID) + `enterprise_uuid` + thông tin NLĐ (`ho_ten`, `ma_so_bhxh`, `ngay_sinh`, `gioi_tinh`, `ma_dinh_danh`, `email`, `dien_thoai`) + thông tin hợp đồng (`cap_bac`, `vi_tri_lam_viec`, `muc_luong`, `phu_cap_*`, `tham_nien_nghe`, `cac_khoan_bo_sung`, `doc_hai_tu_ngay`, `doc_hai_den_ngay`, `loai_hop_dong`, `ma_so_hop_dong`, `loai_bao_cao`, `uuid_hop_dong`, `ngay_hieu_luc`, `ngay_het_hieu_luc`, `thoi_gian_bat_dau_bhxh`, `thoi_gian_ket_thuc_bhxh`, `ghi_chu`, `phuong_thuc_ky_ket`) + `trang_thai`.

### 5.4. Bảng `transactions`
| Column | Type | Ghi chú |
|--------|------|---------|
| transaction_id | VARCHAR(40) | PK (vd `insert_20260626071354_5wrSp1`) |
| loai_giao_dich | VARCHAR | `insert` / `insertlo` / `update` / `upload` / `deletefile` |
| trang_thai | VARCHAR | `Thành công`, ... |
| thong_bao | VARCHAR(1000) | |
| ma_loi | VARCHAR | `E00`, ... |
| ket_qua_xu_ly | VARCHAR(1000) | |
| hopdong_uuid | VARCHAR(64) | UUID hợp đồng liên quan |
| ma_so_hop_dong | VARCHAR | |
| created_at / updated_at | TIMESTAMP | |

### 5.5. Bảng `attachments`
PK `uuid` (UUID) + `hop_dong_uuid`, `file_name`, `file_path`, `file_size`, `created_at`.

### 5.6. Bảng danh mục
`provinces` (`id, ma, ten, trang_thai`), `wards` (`id, ma, ten, tinh_thanh_id, tinh_thanh_string, trang_thai`), `business_sectors` (`id, ma, ten, trang_thai`), `industries` (`id, ma, ten, parent_id, parent_string, linh_vuc_kinh_doanh_id, linh_vuc_kinh_doanh_string, trang_thai`), `enterprise_types` (`id, ma, ten, loai, trang_thai, ten_tieng_anh`).

> Các bảng danh mục hiện **chưa có seed data**.

---

## 6. Truy cập Database trực tiếp (H2 Console)

URL: **https://yuko-unremonstrated-noah.ngrok-free.dev/h2-console**

| Field | Value |
|-------|-------|
| **Driver Class** | `org.h2.Driver` |
| **JDBC URL** | `jdbc:h2:file:./data/hdldb` |
| **User Name** | `sa` |
| **Password** | *(để trống)* |

> H2 Console tắt ở production profile.

---

## 7. Cấu trúc project (Source code)

```
moha-simulator/
├── src/main/java/com/example/hdld/
│   ├── HdldApplication.java
│   ├── application/
│   │   ├── dto/
│   │   │   ├── RootResponse.java                    -- Base response (status số, error_code, message ở root)
│   │   │   ├── request/                             -- Request DTOs
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── ChangePasswordRequest.java
│   │   │   │   ├── CreateEnterpriseRequest.java
│   │   │   │   ├── UpdateEnterpriseRequest.java
│   │   │   │   ├── CreateContractRequest.java
│   │   │   │   ├── BulkCreateContractRequest.java
│   │   │   │   ├── UpdateContractRequest.java        -- {uuid_hop_dong, loai_thay_doi, thong_tin_thay_doi}
│   │   │   │   ├── GetContractRequest.java
│   │   │   │   ├── UploadAttachmentRequest.java
│   │   │   │   ├── DeleteAttachmentRequest.java
│   │   │   │   └── CheckTransactionRequest.java
│   │   │   └── response/                            -- Response DTOs (đa số extends RootResponse)
│   │   │       ├── LoginResponse.java
│   │   │       ├── ChangePasswordResponse.java       -- {status, token, msg}
│   │   │       ├── CreateEnterpriseResponse.java      -- Doanhnghiep_uuid (D hoa)
│   │   │       ├── UpdateEnterpriseResponse.java      -- doanhnghiep_uuid (d thường)
│   │   │       ├── CreateContractResponse.java        -- transaction_id, hopdong_uuid:null, mahopdong:null
│   │   │       ├── UpdateContractResponse.java        -- hopdong_uuid, ma_giao_dich
│   │   │       ├── GetContractResponse.java           -- ma_giao_dich, uuid_hop_dong, data{...}
│   │   │       ├── UploadFileResponse.java            -- ma_giao_dich, uuid_file
│   │   │       ├── DeleteFileResponse.java            -- status 203, ma_giao_dich, uuid_file
│   │   │       ├── CheckTransactionResponse.java      -- ma_giao_dich, data[]
│   │   │       ├── ProvinceResponse.java
│   │   │       ├── WardResponse.java / WardPagingResponse.java
│   │   │       ├── SectorResponse.java
│   │   │       ├── IndustryResponse.java
│   │   │       └── EnterpriseTypeResponse.java
│   │   ├── mapper/                                  -- ContractMapper, EnterpriseMapper, ReferenceDataMapper
│   │   ├── port/                                    -- PasswordEncoderPort, TokenPort, FileStoragePort
│   │   └── usecase/                                 -- Use cases (async: ghi → tạo Transaction)
│   ├── domain/
│   │   ├── entity/                                  -- ... Transaction (+ma_loi, ket_qua_xu_ly, hopdong_uuid, ma_so_hop_dong)
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── service/                                -- EnterpriseDomainService, ContractDomainService, TransactionIdGenerator
│   │   └── valueobject/                             -- ... TransactionId ([A-Za-z0-9_], ≤40)
│   ├── infrastructure/
│   │   ├── config/
│   │   │   ├── UseCaseConfig.java                   -- Bean wiring
│   │   │   ├── JacksonDateConfig.java                -- ddMMyyyy in / dd/MM/yyyy out, blank → null
│   │   │   └── OpenApiConfig.java
│   │   ├── exception/GlobalExceptionHandler.java     -- trả RootResponse, status số, E02/E03/E06
│   │   ├── persistence/ (adapter + entity)
│   │   ├── security/
│   │   └── storage/LocalFileStorageAdapter.java
│   └── presentation/controller/                     -- 6 controllers
├── src/main/resources/ (application.properties, application-prod.properties)
├── src/test/ (unit + integration tests)
└── pom.xml
```

---

## 8. Ví dụ curl nhanh

### Login (lưu token từ root, không phải $.data)
```bash
curl -s https://yuko-unremonstrated-noah.ngrok-free.dev/hdld/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'
```

### Thêm mới doanh nghiệp
```bash
TOKEN="eyJhbGciOiJIUzI1NiJ9..."
curl -s https://yuko-unremonstrated-noah.ngrok-free.dev/hdld/ThemMoiDoanhNghiep \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "ten_doanh_nghiep": "Cong ty TNHH ABC",
    "loai_hinh_doanh_nghiep": "TNHH",
    "dia_chi": "123 Duong Le Loi",
    "ma_tinh": "HNI",
    "ma_xa": "DD",
    "nguoi_dai_dien": "Nguyen Van A",
    "dien_thoai": "0909123456",
    "email": "info@abc.com",
    "ma_so_thue": "1234567890",
    "ma_linh_vuc": "A",
    "ma_nganh_nghe": "0111"
  }'
```

### Tạo hợp đồng rồi kiểm tra giao dịch
```bash
# 1) Tạo HĐLĐ (ngày dạng ddmmyyyy) → nhận transaction_id
curl -s https://yuko-unremonstrated-noah.ngrok-free.dev/hdld/ThemMoiHopDongLaoDong \
  -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" \
  -d '{"doanhnghiep_uuid":"...","thong_tin_nld":{"ho_ten":"A","ma_dinh_danh":"123456789","ngay_sinh":"27041986","gioi_tinh":"Nam"},"thong_tin_hop_dong":{"muc_luong":"20000000","ngay_hieu_luc":"01012024","loai_hop_dong":"2","ma_so_hop_dong":"HD1"}}'

# 2) Poll kết quả
curl -s https://yuko-unremonstrated-noah.ngrok-free.dev/hdld/KiemTraTrangThaiGiaoDich \
  -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" \
  -d '{"ma_giao_dich":"insert_20260626071354_5wrSp1"}'
```

### Lấy danh sách tỉnh thành (mảng ở root)
```bash
curl -s https://yuko-unremonstrated-noah.ngrok-free.dev/hdld/danh-muc-tinh/list \
  -H "Authorization: Bearer $TOKEN"
```

---

## 9. Lưu ý khác

- **Token hết hạn sau 24h** — login lại nếu gặp 401/E01.
- **Mô hình async**: API ghi không trả kết quả ngay; luôn poll `KiemTraTrangThaiGiaoDich` bằng `ma_giao_dich`.
- **Ngày tháng**: gửi `ddmmyyyy`, nhận `dd/MM/yyyy`; gửi `""` cho ngày trống (sẽ thành `null`).
- **Secret JWT mặc định** chỉ dùng cho dev; production set `JWT_SECRET`.
- **H2 file-based**: dữ liệu ở `data/`; file upload ở `./data/attachments/`. Nếu đổi schema gây lỗi cột cũ, xóa `data/hdldb.mv.db` để tạo lại.
- **Kiến trúc**: Hexagonal (Ports & Adapters), 3 layer `presentation` → `application` → `domain`.

