package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO for POST /hdld/ThongTinHopDong.
 *
 * <p>Per the official contract the contract details are returned in a single flat
 * {@code data} object (employee + contract fields merged, dates as {@code dd/MM/yyyy}),
 * alongside root {@code ma_giao_dich} and {@code uuid_hop_dong}.
 */
public class GetContractResponse extends RootResponse {

    @JsonProperty("ma_giao_dich")
    private String maGiaoDich;

    @JsonProperty("uuid_hop_dong")
    private String uuidHopDong;

    @JsonProperty("data")
    private ContractDetail data;

    public GetContractResponse() {
    }

    public GetContractResponse(String maGiaoDich, String uuidHopDong, ContractDetail data) {
        super(200, "E00", "Thành công");
        this.maGiaoDich = maGiaoDich;
        this.uuidHopDong = uuidHopDong;
        this.data = data;
    }

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }

    public String getUuidHopDong() { return uuidHopDong; }
    public void setUuidHopDong(String uuidHopDong) { this.uuidHopDong = uuidHopDong; }

    public ContractDetail getData() { return data; }
    public void setData(ContractDetail data) { this.data = data; }

    /** Flat merged employee + contract detail (the {@code data} object). */
    public static class ContractDetail {

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
        @JsonProperty("ngay_cham_dut_hop_dong")
        private LocalDate ngayChamDutHopDong;
        @JsonProperty("thoi_gian_bat_dau_bhxh")
        private LocalDate thoiGianBatDauBhxh;
        @JsonProperty("thoi_gian_ket_thuc_bhxh")
        private LocalDate thoiGianKetThucBhxh;
        @JsonProperty("ghi_chu")
        private String ghiChu;
        @JsonProperty("phuong_thuc_ky_ket")
        private String phuongThucKyKet;
        @JsonProperty("ngay_app_dung_dia_diem_moi")
        private LocalDate ngayApDungDiaDiemMoi;

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

        public LocalDate getNgayChamDutHopDong() { return ngayChamDutHopDong; }
        public void setNgayChamDutHopDong(LocalDate ngayChamDutHopDong) { this.ngayChamDutHopDong = ngayChamDutHopDong; }

        public LocalDate getThoiGianBatDauBhxh() { return thoiGianBatDauBhxh; }
        public void setThoiGianBatDauBhxh(LocalDate thoiGianBatDauBhxh) { this.thoiGianBatDauBhxh = thoiGianBatDauBhxh; }

        public LocalDate getThoiGianKetThucBhxh() { return thoiGianKetThucBhxh; }
        public void setThoiGianKetThucBhxh(LocalDate thoiGianKetThucBhxh) { this.thoiGianKetThucBhxh = thoiGianKetThucBhxh; }

        public String getGhiChu() { return ghiChu; }
        public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

        public String getPhuongThucKyKet() { return phuongThucKyKet; }
        public void setPhuongThucKyKet(String phuongThucKyKet) { this.phuongThucKyKet = phuongThucKyKet; }

        public LocalDate getNgayApDungDiaDiemMoi() { return ngayApDungDiaDiemMoi; }
        public void setNgayApDungDiaDiemMoi(LocalDate ngayApDungDiaDiemMoi) { this.ngayApDungDiaDiemMoi = ngayApDungDiaDiemMoi; }
    }
}
