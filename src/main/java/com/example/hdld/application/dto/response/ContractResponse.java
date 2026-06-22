package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO for contract operations.
 */
public class ContractResponse {

    @JsonProperty("contract_uuid")
    private String contractUuid;

    @JsonProperty("enterprise_uuid")
    private String enterpriseUuid;

    @JsonProperty("trang_thai")
    private String trangThai;

    @JsonProperty("thong_tin_nld")
    private EmployeeInfoResponse thongTinNld;

    @JsonProperty("thong_tin_hop_dong")
    private ContractInfoResponse thongTinHopDong;

    public ContractResponse() {
    }

    public String getContractUuid() { return contractUuid; }
    public void setContractUuid(String contractUuid) { this.contractUuid = contractUuid; }

    public String getEnterpriseUuid() { return enterpriseUuid; }
    public void setEnterpriseUuid(String enterpriseUuid) { this.enterpriseUuid = enterpriseUuid; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public EmployeeInfoResponse getThongTinNld() { return thongTinNld; }
    public void setThongTinNld(EmployeeInfoResponse thongTinNld) { this.thongTinNld = thongTinNld; }

    public ContractInfoResponse getThongTinHopDong() { return thongTinHopDong; }
    public void setThongTinHopDong(ContractInfoResponse thongTinHopDong) { this.thongTinHopDong = thongTinHopDong; }

    public static class EmployeeInfoResponse {
        @JsonProperty("ho_ten")
        private String hoTen;

        @JsonProperty("ma_so_bhxh")
        private String maSoBhxh;

        @JsonProperty("ngay_sinh")
        private LocalDate ngaySinh;

        @JsonProperty("gioi_tinh")
        private String gioiTinh;

        @JsonProperty("ma_dinh_danh")
        private String maDinhDanh;

        @JsonProperty("email")
        private String email;

        @JsonProperty("dien_thoai")
        private String dienThoai;

        public EmployeeInfoResponse() {
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

    public static class ContractInfoResponse {
        @JsonProperty("cap_bac")
        private String capBac;

        @JsonProperty("vi_tri_lam_viec")
        private String viTriLamViec;

        @JsonProperty("muc_luong")
        private BigDecimal mucLuong;

        @JsonProperty("phu_cap_chuc_vu")
        private BigDecimal phuCapChucVu;

        @JsonProperty("phu_cap_tham_nien")
        private BigDecimal phuCapThamNien;

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

        @JsonProperty("loai_hop_dong")
        private String loaiHopDong;

        @JsonProperty("ma_so_hop_dong")
        private String maSoHopDong;

        @JsonProperty("loai_bao_cao")
        private String loaiBaoCao;

        @JsonProperty("uuid_hop_dong")
        private String uuidHopDong;

        @JsonProperty("ngay_hieu_luc")
        private LocalDate ngayHieuLuc;

        @JsonProperty("ngay_het_hieu_luc")
        private LocalDate ngayHetHieuLuc;

        @JsonProperty("thoi_gian_bat_dau_bhxh")
        private LocalDate thoiGianBatDauBhxh;

        @JsonProperty("thoi_gian_ket_thuc_bhxh")
        private LocalDate thoiGianKetThucBhxh;

        @JsonProperty("ghi_chu")
        private String ghiChu;

        @JsonProperty("phuong_thuc_ky_ket")
        private String phuongThucKyKet;

        public ContractInfoResponse() {
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
