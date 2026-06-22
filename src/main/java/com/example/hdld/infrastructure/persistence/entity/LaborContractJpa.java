package com.example.hdld.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "labor_contracts")
public class LaborContractJpa {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "enterprise_uuid", nullable = false)
    private UUID enterpriseUuid;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_id_number")
    private String employeeIdNumber;

    @Column(name = "employee_dob")
    private LocalDate employeeDob;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_bhxh")
    private String employeeBhxh;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "position")
    private String position;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "status")
    private String status;

    @Column(name = "cap_bac")
    private String capBac;

    @Column(name = "vi_tri_lam_viec")
    private String viTriLamViec;

    @Column(name = "muc_luong")
    private BigDecimal mucLuong;

    @Column(name = "phu_cap_chuc_vu")
    private BigDecimal phuCapChucVu;

    @Column(name = "phu_cap_tham_nien")
    private BigDecimal phuCapThamNien;

    @Column(name = "tham_nien_nghe")
    private String thamNienNghe;

    @Column(name = "phu_cap_luong")
    private BigDecimal phuCapLuong;

    @Column(name = "cac_khoan_bo_sung")
    private BigDecimal cacKhoanBoSung;

    @Column(name = "doc_hai_tu_ngay")
    private LocalDate docHaiTuNgay;

    @Column(name = "doc_hai_den_ngay")
    private LocalDate docHaiDenNgay;

    @Column(name = "loai_hop_dong")
    private String loaiHopDong;

    @Column(name = "ma_so_hop_dong")
    private String maSoHopDong;

    @Column(name = "loai_bao_cao")
    private String loaiBaoCao;

    @Column(name = "uuid_hop_dong")
    private String uuidHopDong;

    @Column(name = "ngay_hieu_luc")
    private LocalDate ngayHieuLuc;

    @Column(name = "ngay_het_hieu_luc")
    private LocalDate ngayHetHieuLuc;

    @Column(name = "thoi_gian_bat_dau_bhxh")
    private LocalDate thoiGianBatDauBhxh;

    @Column(name = "thoi_gian_ket_thuc_bhxh")
    private LocalDate thoiGianKetThucBhxh;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "phuong_thuc_ky_ket")
    private String phuongThucKyKet;

    public LaborContractJpa() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getEnterpriseUuid() {
        return enterpriseUuid;
    }

    public void setEnterpriseUuid(UUID enterpriseUuid) {
        this.enterpriseUuid = enterpriseUuid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public LocalDate getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(LocalDate employeeDob) {
        this.employeeDob = employeeDob;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeBhxh() {
        return employeeBhxh;
    }

    public void setEmployeeBhxh(String employeeBhxh) {
        this.employeeBhxh = employeeBhxh;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapBac() {
        return capBac;
    }

    public void setCapBac(String capBac) {
        this.capBac = capBac;
    }

    public String getViTriLamViec() {
        return viTriLamViec;
    }

    public void setViTriLamViec(String viTriLamViec) {
        this.viTriLamViec = viTriLamViec;
    }

    public BigDecimal getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(BigDecimal mucLuong) {
        this.mucLuong = mucLuong;
    }

    public BigDecimal getPhuCapChucVu() {
        return phuCapChucVu;
    }

    public void setPhuCapChucVu(BigDecimal phuCapChucVu) {
        this.phuCapChucVu = phuCapChucVu;
    }

    public BigDecimal getPhuCapThamNien() {
        return phuCapThamNien;
    }

    public void setPhuCapThamNien(BigDecimal phuCapThamNien) {
        this.phuCapThamNien = phuCapThamNien;
    }

    public String getThamNienNghe() {
        return thamNienNghe;
    }

    public void setThamNienNghe(String thamNienNghe) {
        this.thamNienNghe = thamNienNghe;
    }

    public BigDecimal getPhuCapLuong() {
        return phuCapLuong;
    }

    public void setPhuCapLuong(BigDecimal phuCapLuong) {
        this.phuCapLuong = phuCapLuong;
    }

    public BigDecimal getCacKhoanBoSung() {
        return cacKhoanBoSung;
    }

    public void setCacKhoanBoSung(BigDecimal cacKhoanBoSung) {
        this.cacKhoanBoSung = cacKhoanBoSung;
    }

    public LocalDate getDocHaiTuNgay() {
        return docHaiTuNgay;
    }

    public void setDocHaiTuNgay(LocalDate docHaiTuNgay) {
        this.docHaiTuNgay = docHaiTuNgay;
    }

    public LocalDate getDocHaiDenNgay() {
        return docHaiDenNgay;
    }

    public void setDocHaiDenNgay(LocalDate docHaiDenNgay) {
        this.docHaiDenNgay = docHaiDenNgay;
    }

    public String getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(String loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
    }

    public String getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(String loaiBaoCao) {
        this.loaiBaoCao = loaiBaoCao;
    }

    public String getUuidHopDong() {
        return uuidHopDong;
    }

    public void setUuidHopDong(String uuidHopDong) {
        this.uuidHopDong = uuidHopDong;
    }

    public LocalDate getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(LocalDate ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public LocalDate getNgayHetHieuLuc() {
        return ngayHetHieuLuc;
    }

    public void setNgayHetHieuLuc(LocalDate ngayHetHieuLuc) {
        this.ngayHetHieuLuc = ngayHetHieuLuc;
    }

    public LocalDate getThoiGianBatDauBhxh() {
        return thoiGianBatDauBhxh;
    }

    public void setThoiGianBatDauBhxh(LocalDate thoiGianBatDauBhxh) {
        this.thoiGianBatDauBhxh = thoiGianBatDauBhxh;
    }

    public LocalDate getThoiGianKetThucBhxh() {
        return thoiGianKetThucBhxh;
    }

    public void setThoiGianKetThucBhxh(LocalDate thoiGianKetThucBhxh) {
        this.thoiGianKetThucBhxh = thoiGianKetThucBhxh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getPhuongThucKyKet() {
        return phuongThucKyKet;
    }

    public void setPhuongThucKyKet(String phuongThucKyKet) {
        this.phuongThucKyKet = phuongThucKyKet;
    }
}
