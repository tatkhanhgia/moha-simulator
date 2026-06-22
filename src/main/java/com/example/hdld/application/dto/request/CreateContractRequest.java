package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for POST /hdld/ThemMoiHopDongLaoDong
 */
public class CreateContractRequest {

    @NotBlank
    @JsonProperty("enterprise_uuid")
    private String enterpriseUuid;

    @Valid
    @NotNull
    @JsonProperty("thong_tin_nld")
    private EmployeeInfoRequest thongTinNld;

    @Valid
    @NotNull
    @JsonProperty("thong_tin_hop_dong")
    private ContractInfoRequest thongTinHopDong;

    public CreateContractRequest() {
    }

    public String getEnterpriseUuid() { return enterpriseUuid; }
    public void setEnterpriseUuid(String enterpriseUuid) { this.enterpriseUuid = enterpriseUuid; }

    public EmployeeInfoRequest getThongTinNld() { return thongTinNld; }
    public void setThongTinNld(EmployeeInfoRequest thongTinNld) { this.thongTinNld = thongTinNld; }

    public ContractInfoRequest getThongTinHopDong() { return thongTinHopDong; }
    public void setThongTinHopDong(ContractInfoRequest thongTinHopDong) { this.thongTinHopDong = thongTinHopDong; }

    /**
     * Nested DTO for employee information.
     */
    public static class EmployeeInfoRequest {
        @NotBlank
        @Size(max = 250)
        @JsonProperty("ho_ten")
        private String hoTen;

        @Size(max = 20)
        @JsonProperty("ma_so_bhxh")
        private String maSoBhxh;

        @NotNull
        @JsonProperty("ngay_sinh")
        private LocalDate ngaySinh;

        @NotBlank
        @Size(max = 20)
        @JsonProperty("gioi_tinh")
        private String gioiTinh;

        @NotBlank
        @Pattern(regexp = "^\\d{9}$|^\\d{12}$", message = "ID number must be 9 or 12 digits")
        @Size(max = 20)
        @JsonProperty("ma_dinh_danh")
        private String maDinhDanh;

        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email format invalid")
        @Size(max = 100)
        @JsonProperty("email")
        private String email;

        @Size(max = 20)
        @JsonProperty("dien_thoai")
        private String dienThoai;

        public EmployeeInfoRequest() {
        }

        public String getHoTen() { return hoTen; }
        public void setHoTen(String hoTen) { this.hoTen = hoTen; }

        public String getMaSoBhxh() { return maSoBhxh; }
        public void setMaSoBhxh(String maSoBhxh) { this.maSoBhxh = maSoBhxh; }

        public LocalDate getNgaySinh() { return ngaySinh; }
        public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

        public String getGioiTinh() { return gioiTinh; }
        public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

        public String getMaDinhDanh() { return maDinhDanh; }
        public void setMaDinhDanh(String maDinhDanh) { this.maDinhDanh = maDinhDanh; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getDienThoai() { return dienThoai; }
        public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }
    }

    /**
     * Nested DTO for contract information.
     */
    public static class ContractInfoRequest {
        @Size(max = 250)
        @JsonProperty("cap_bac")
        private String capBac;

        @Size(max = 250)
        @JsonProperty("vi_tri_lam_viec")
        private String viTriLamViec;

        @NotNull
        @JsonProperty("muc_luong")
        private BigDecimal mucLuong;

        @JsonProperty("phu_cap_chuc_vu")
        private BigDecimal phuCapChucVu;

        @JsonProperty("phu_cap_tham_nien")
        private BigDecimal phuCapThamNien;

        @Size(max = 250)
        @JsonProperty("tham_nien_nghe")
        private String thamNienNghe;

        @JsonProperty("phu_cap_luong")
        private BigDecimal phuCapLuong;

        @JsonProperty("cac_khoan_bo_sung")
        private BigDecimal cacKhoanBoSung;

        @JsonProperty("doc_hai_tu_ngay")
        private LocalDate docHaiTuNgay;

        @JsonProperty("doc_hai_den_ngay")
        private LocalDate docHaiDenNgay;

        @NotBlank
        @NotBlank
        @Size(max = 20)
        @JsonProperty("loai_hop_dong")
        private String loaiHopDong;

        @Size(max = 50)
        @JsonProperty("ma_so_hop_dong")
        private String maSoHopDong;

        @Size(max = 20)
        @JsonProperty("loai_bao_cao")
        private String loaiBaoCao;

        @Size(max = 50)
        @JsonProperty("uuid_hop_dong")
        private String uuidHopDong;

        @NotNull
        @JsonProperty("ngay_hieu_luc")
        private LocalDate ngayHieuLuc;

        @JsonProperty("ngay_het_hieu_luc")
        private LocalDate ngayHetHieuLuc;

        @JsonProperty("thoi_gian_bat_dau_bhxh")
        private LocalDate thoiGianBatDauBhxh;

        @JsonProperty("thoi_gian_ket_thuc_bhxh")
        private LocalDate thoiGianKetThucBhxh;

        @Size(max = 4000)
        @JsonProperty("ghi_chu")
        private String ghiChu;

        @Size(max = 50)
        @JsonProperty("phuong_thuc_ky_ket")
        private String phuongThucKyKet;

        public ContractInfoRequest() {
        }

        public String getCapBac() { return capBac; }
        public void setCapBac(String capBac) { this.capBac = capBac; }

        public String getViTriLamViec() { return viTriLamViec; }
        public void setViTriLamViec(String viTriLamViec) { this.viTriLamViec = viTriLamViec; }

        public BigDecimal getMucLuong() { return mucLuong; }
        public void setMucLuong(BigDecimal mucLuong) { this.mucLuong = mucLuong; }

        public BigDecimal getPhuCapChucVu() { return phuCapChucVu; }
        public void setPhuCapChucVu(BigDecimal phuCapChucVu) { this.phuCapChucVu = phuCapChucVu; }

        public BigDecimal getPhuCapThamNien() { return phuCapThamNien; }
        public void setPhuCapThamNien(BigDecimal phuCapThamNien) { this.phuCapThamNien = phuCapThamNien; }

        public String getThamNienNghe() { return thamNienNghe; }
        public void setThamNienNghe(String thamNienNghe) { this.thamNienNghe = thamNienNghe; }

        public BigDecimal getPhuCapLuong() { return phuCapLuong; }
        public void setPhuCapLuong(BigDecimal phuCapLuong) { this.phuCapLuong = phuCapLuong; }

        public BigDecimal getCacKhoanBoSung() { return cacKhoanBoSung; }
        public void setCacKhoanBoSung(BigDecimal cacKhoanBoSung) { this.cacKhoanBoSung = cacKhoanBoSung; }

        public LocalDate getDocHaiTuNgay() { return docHaiTuNgay; }
        public void setDocHaiTuNgay(LocalDate docHaiTuNgay) { this.docHaiTuNgay = docHaiTuNgay; }

        public LocalDate getDocHaiDenNgay() { return docHaiDenNgay; }
        public void setDocHaiDenNgay(LocalDate docHaiDenNgay) { this.docHaiDenNgay = docHaiDenNgay; }

        public String getLoaiHopDong() { return loaiHopDong; }
        public void setLoaiHopDong(String loaiHopDong) { this.loaiHopDong = loaiHopDong; }

        public String getMaSoHopDong() { return maSoHopDong; }
        public void setMaSoHopDong(String maSoHopDong) { this.maSoHopDong = maSoHopDong; }

        public String getLoaiBaoCao() { return loaiBaoCao; }
        public void setLoaiBaoCao(String loaiBaoCao) { this.loaiBaoCao = loaiBaoCao; }

        public String getUuidHopDong() { return uuidHopDong; }
        public void setUuidHopDong(String uuidHopDong) { this.uuidHopDong = uuidHopDong; }

        public LocalDate getNgayHieuLuc() { return ngayHieuLuc; }
        public void setNgayHieuLuc(LocalDate ngayHieuLuc) { this.ngayHieuLuc = ngayHieuLuc; }

        public LocalDate getNgayHetHieuLuc() { return ngayHetHieuLuc; }
        public void setNgayHetHieuLuc(LocalDate ngayHetHieuLuc) { this.ngayHetHieuLuc = ngayHetHieuLuc; }

        public LocalDate getThoiGianBatDauBhxh() { return thoiGianBatDauBhxh; }
        public void setThoiGianBatDauBhxh(LocalDate thoiGianBatDauBhxh) { this.thoiGianBatDauBhxh = thoiGianBatDauBhxh; }

        public LocalDate getThoiGianKetThucBhxh() { return thoiGianKetThucBhxh; }
        public void setThoiGianKetThucBhxh(LocalDate thoiGianKetThucBhxh) { this.thoiGianKetThucBhxh = thoiGianKetThucBhxh; }

        public String getGhiChu() { return ghiChu; }
        public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

        public String getPhuongThucKyKet() { return phuongThucKyKet; }
        public void setPhuongThucKyKet(String phuongThucKyKet) { this.phuongThucKyKet = phuongThucKyKet; }
    }
}
