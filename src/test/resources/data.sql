-- Test data for integration tests

-- Test user (password: password123 hashed with BCrypt)
INSERT INTO users (username, password_hash, role) VALUES
('testuser', '$2a$10$Hb0zUZBAnq6oFWQqYpQAC.Z7x8rQxIMIJMzx.LBg7iHEcbDwNjp7i', 'ROLE_USER');

-- Provinces
INSERT INTO provinces (ma, ten, trang_thai) VALUES
('01', 'Ha Noi', 'active'),
('79', 'Ho Chi Minh', 'active');

-- Wards
INSERT INTO wards (ma, ten, tinh_thanh_id, tinh_thanh_string, trang_thai) VALUES
('001', 'Phuong 1', '01', 'Ha Noi', 'active'),
('002', 'Phuong 2', '01', 'Ha Noi', 'active'),
('790', 'Phuong Ben Nghe', '79', 'Ho Chi Minh', 'active');

-- Business sectors
INSERT INTO business_sectors (ma, ten, trang_thai) VALUES
('A', 'Nong nghiep', 'active'),
('B', 'Cong nghiep', 'active');

-- Industries
INSERT INTO industries (ma, ten, parent_id, parent_string, linh_vuc_kinh_doanh_id, linh_vuc_kinh_doanh_string, trang_thai) VALUES
('011', 'Trong trot', '01', 'Nong nghiep', 'A', 'Nong nghiep', 'active'),
('071', 'Khai thac than', '07', 'Khai khoang', 'B', 'Cong nghiep', 'active');

-- Enterprise types
INSERT INTO enterprise_types (ma, ten, loai, trang_thai, ten_tieng_anh) VALUES
('DN', 'Doanh nghiep', 'loai_hinh', 'active', 'Enterprise'),
('HTX', 'Hop tac xa', 'loai_hinh', 'active', 'Cooperative');

-- One enterprise for contract tests
INSERT INTO enterprises (uuid, ten_doanh_nghiep, loai_hinh_doanh_nghiep, dia_chi, ma_tinh, ma_xa, ma_so_thue, ma_linh_vuc, ma_nganh_nghe, created_at, updated_at) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Test Enterprise', 'DN', '123 Test St', '01', '001', '1234567890', 'A', '011', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- One labor contract for attachment tests
INSERT INTO labor_contracts (uuid, enterprise_uuid, employee_name, employee_id_number, employee_dob, employee_gender, loai_hop_dong, ngay_hieu_luc, status) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Nguyen Van A', '123456789', '1990-01-01', 'Nam', 'HDTV', '2024-01-01', 'active');

-- One transaction for status check tests
INSERT INTO transactions (transaction_id, loai_giao_dich, trang_thai, thong_bao, created_at, updated_at) VALUES
('TXN123', 'CREATE_CONTRACT', 'completed', 'Transaction completed successfully', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
