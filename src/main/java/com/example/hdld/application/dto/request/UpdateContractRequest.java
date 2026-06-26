package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for POST /hdld/CapNhatHopDongLaoDong.
 *
 * <p>Per the official contract an update is a typed change request: {@code uuid_hop_dong}
 * identifies the contract, {@code loai_thay_doi} selects the kind of change (1=lương,
 * 2=gia hạn, 3=chấm dứt, 4=vị trí, 5=BHXH), and {@code thong_tin_thay_doi} carries the
 * fields relevant to that kind (a superset DTO; only the relevant fields are populated).
 */
public class UpdateContractRequest {

    @NotBlank
    @JsonProperty("uuid_hop_dong")
    private String uuidHopDong;

    @NotNull
    @JsonProperty("loai_thay_doi")
    private Integer loaiThayDoi;

    @Valid
    @NotNull
    @JsonProperty("thong_tin_thay_doi")
    private ChangeInfo thongTinThayDoi;

    public UpdateContractRequest() {
    }

    public String getUuidHopDong() { return uuidHopDong; }
    public void setUuidHopDong(String uuidHopDong) { this.uuidHopDong = uuidHopDong; }

    public Integer getLoaiThayDoi() { return loaiThayDoi; }
    public void setLoaiThayDoi(Integer loaiThayDoi) { this.loaiThayDoi = loaiThayDoi; }

    public ChangeInfo getThongTinThayDoi() { return thongTinThayDoi; }
    public void setThongTinThayDoi(ChangeInfo thongTinThayDoi) { this.thongTinThayDoi = thongTinThayDoi; }

    /** Superset of the per-change-type fields described in the PDF. */
    public static class ChangeInfo {

        // loai_thay_doi = 1 (thay đổi lương) ----------------------------------
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
        @JsonProperty("ngay_hieu_luc")
        private LocalDate ngayHieuLuc;

        // loai_thay_doi = 2 (gia hạn) -----------------------------------------
        @JsonProperty("ngay_ket_thuc_moi")
        private LocalDate ngayKetThucMoi;

        // loai_thay_doi = 3 (chấm dứt) ----------------------------------------
        @JsonProperty("ngay_cham_dut_hop_dong")
        private LocalDate ngayChamDutHopDong;

        // loai_thay_doi = 4 (thay đổi vị trí) ---------------------------------
        @JsonProperty("vi_tri_lam_viec")
        private String viTriLamViec;
        @JsonProperty("cap_bac")
        private String capBac;
        @JsonProperty("ngay_app_dung_dia_diem_moi")
        private LocalDate ngayApDungDiaDiemMoi;

        // loai_thay_doi = 5 (cập nhật BHXH) -----------------------------------
        @JsonProperty("ma_so_bhxh")
        private String maSoBhxh;
        @JsonProperty("thoi_gian_bat_dau_bhxh")
        private LocalDate thoiGianBatDauBhxh;
        @JsonProperty("thoi_gian_ket_thuc_bhxh")
        private LocalDate thoiGianKetThucBhxh;

        // common --------------------------------------------------------------
        @JsonProperty("ly_do")
        private String lyDo;

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

        public LocalDate getNgayHieuLuc() { return ngayHieuLuc; }
        public void setNgayHieuLuc(LocalDate ngayHieuLuc) { this.ngayHieuLuc = ngayHieuLuc; }

        public LocalDate getNgayKetThucMoi() { return ngayKetThucMoi; }
        public void setNgayKetThucMoi(LocalDate ngayKetThucMoi) { this.ngayKetThucMoi = ngayKetThucMoi; }

        public LocalDate getNgayChamDutHopDong() { return ngayChamDutHopDong; }
        public void setNgayChamDutHopDong(LocalDate ngayChamDutHopDong) { this.ngayChamDutHopDong = ngayChamDutHopDong; }

        public String getViTriLamViec() { return viTriLamViec; }
        public void setViTriLamViec(String viTriLamViec) { this.viTriLamViec = viTriLamViec; }

        public String getCapBac() { return capBac; }
        public void setCapBac(String capBac) { this.capBac = capBac; }

        public LocalDate getNgayApDungDiaDiemMoi() { return ngayApDungDiaDiemMoi; }
        public void setNgayApDungDiaDiemMoi(LocalDate ngayApDungDiaDiemMoi) { this.ngayApDungDiaDiemMoi = ngayApDungDiaDiemMoi; }

        public String getMaSoBhxh() { return maSoBhxh; }
        public void setMaSoBhxh(String maSoBhxh) { this.maSoBhxh = maSoBhxh; }

        public LocalDate getThoiGianBatDauBhxh() { return thoiGianBatDauBhxh; }
        public void setThoiGianBatDauBhxh(LocalDate thoiGianBatDauBhxh) { this.thoiGianBatDauBhxh = thoiGianBatDauBhxh; }

        public LocalDate getThoiGianKetThucBhxh() { return thoiGianKetThucBhxh; }
        public void setThoiGianKetThucBhxh(LocalDate thoiGianKetThucBhxh) { this.thoiGianKetThucBhxh = thoiGianKetThucBhxh; }

        public String getLyDo() { return lyDo; }
        public void setLyDo(String lyDo) { this.lyDo = lyDo; }
    }
}
