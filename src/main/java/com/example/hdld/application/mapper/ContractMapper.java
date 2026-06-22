package com.example.hdld.application.mapper;

import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.request.UpdateContractRequest;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.domain.entity.ContractInfo;
import com.example.hdld.domain.entity.EmployeeInfo;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;

/**
 * Maps between LaborContract domain entity and DTOs.
 */
public class ContractMapper {

    private ContractMapper() {
    }

    public static LaborContract toDomain(CreateContractRequest dto) {
        if (dto == null) {
            return null;
        }
        return new LaborContract(
                new ContractUuid(java.util.UUID.randomUUID()),
                new EnterpriseUuid(dto.getEnterpriseUuid()),
                toDomain(dto.getThongTinNld()),
                toDomain(dto.getThongTinHopDong()),
                "active"
        );
    }

    public static EmployeeInfo toDomain(CreateContractRequest.EmployeeInfoRequest dto) {
        if (dto == null) {
            return null;
        }
        return new EmployeeInfo(
                dto.getHoTen(),
                dto.getMaSoBhxh(),
                dto.getNgaySinh(),
                dto.getGioiTinh(),
                dto.getMaDinhDanh(),
                dto.getEmail() != null ? new Email(dto.getEmail()) : null,
                dto.getDienThoai()
        );
    }

    public static ContractInfo toDomain(CreateContractRequest.ContractInfoRequest dto) {
        if (dto == null) {
            return null;
        }
        ContractInfo info = new ContractInfo();
        info.setCapBac(dto.getCapBac());
        info.setViTriLamViec(dto.getViTriLamViec());
        info.setMucLuong(dto.getMucLuong());
        info.setPhuCapChucVu(dto.getPhuCapChucVu());
        info.setPhuCapThamNien(dto.getPhuCapThamNien());
        info.setThamNienNghe(dto.getThamNienNghe());
        info.setPhuCapLuong(dto.getPhuCapLuong());
        info.setCacKhoanBoSung(dto.getCacKhoanBoSung());
        info.setDocHaiTuNgay(dto.getDocHaiTuNgay());
        info.setDocHaiDenNgay(dto.getDocHaiDenNgay());
        info.setLoaiHopDong(dto.getLoaiHopDong());
        info.setMaSoHopDong(dto.getMaSoHopDong());
        info.setLoaiBaoCao(dto.getLoaiBaoCao());
        info.setUuidHopDong(dto.getUuidHopDong());
        info.setNgayHieuLuc(dto.getNgayHieuLuc());
        info.setNgayHetHieuLuc(dto.getNgayHetHieuLuc());
        info.setThoiGianBatDauBhxh(dto.getThoiGianBatDauBhxh());
        info.setThoiGianKetThucBhxh(dto.getThoiGianKetThucBhxh());
        info.setGhiChu(dto.getGhiChu());
        info.setPhuongThucKyKet(dto.getPhuongThucKyKet());
        return info;
    }

    public static void applyUpdate(LaborContract entity, UpdateContractRequest dto) {
        if (entity == null || dto == null) {
            return;
        }
        if (dto.getEnterpriseUuid() != null) {
            entity.setDoanhNghiepUuid(new EnterpriseUuid(dto.getEnterpriseUuid()));
        }
        if (dto.getThongTinNld() != null) {
            applyEmployeeUpdate(entity.getThongTinNld(), dto.getThongTinNld());
        }
        if (dto.getThongTinHopDong() != null) {
            applyContractInfoUpdate(entity.getThongTinHopDong(), dto.getThongTinHopDong());
        }
    }

    private static void applyEmployeeUpdate(EmployeeInfo entity, UpdateContractRequest.EmployeeInfoUpdateRequest dto) {
        if (dto.getHoTen() != null) entity.setHoTen(dto.getHoTen());
        if (dto.getMaSoBhxh() != null) entity.setMaSoBhxh(dto.getMaSoBhxh());
        if (dto.getNgaySinh() != null) entity.setNgaySinh(dto.getNgaySinh());
        if (dto.getGioiTinh() != null) entity.setGioiTinh(dto.getGioiTinh());
        if (dto.getMaDinhDanh() != null) entity.setMaDinhDanh(dto.getMaDinhDanh());
        if (dto.getEmail() != null) entity.setEmail(new Email(dto.getEmail()));
        if (dto.getDienThoai() != null) entity.setDienThoai(dto.getDienThoai());
    }

    private static void applyContractInfoUpdate(ContractInfo entity, UpdateContractRequest.ContractInfoUpdateRequest dto) {
        if (dto.getCapBac() != null) entity.setCapBac(dto.getCapBac());
        if (dto.getViTriLamViec() != null) entity.setViTriLamViec(dto.getViTriLamViec());
        if (dto.getMucLuong() != null) entity.setMucLuong(dto.getMucLuong());
        if (dto.getPhuCapChucVu() != null) entity.setPhuCapChucVu(dto.getPhuCapChucVu());
        if (dto.getPhuCapThamNien() != null) entity.setPhuCapThamNien(dto.getPhuCapThamNien());
        if (dto.getThamNienNghe() != null) entity.setThamNienNghe(dto.getThamNienNghe());
        if (dto.getPhuCapLuong() != null) entity.setPhuCapLuong(dto.getPhuCapLuong());
        if (dto.getCacKhoanBoSung() != null) entity.setCacKhoanBoSung(dto.getCacKhoanBoSung());
        if (dto.getDocHaiTuNgay() != null) entity.setDocHaiTuNgay(dto.getDocHaiTuNgay());
        if (dto.getDocHaiDenNgay() != null) entity.setDocHaiDenNgay(dto.getDocHaiDenNgay());
        if (dto.getLoaiHopDong() != null) entity.setLoaiHopDong(dto.getLoaiHopDong());
        if (dto.getMaSoHopDong() != null) entity.setMaSoHopDong(dto.getMaSoHopDong());
        if (dto.getLoaiBaoCao() != null) entity.setLoaiBaoCao(dto.getLoaiBaoCao());
        if (dto.getUuidHopDong() != null) entity.setUuidHopDong(dto.getUuidHopDong());
        if (dto.getNgayHieuLuc() != null) entity.setNgayHieuLuc(dto.getNgayHieuLuc());
        if (dto.getNgayHetHieuLuc() != null) entity.setNgayHetHieuLuc(dto.getNgayHetHieuLuc());
        if (dto.getThoiGianBatDauBhxh() != null) entity.setThoiGianBatDauBhxh(dto.getThoiGianBatDauBhxh());
        if (dto.getThoiGianKetThucBhxh() != null) entity.setThoiGianKetThucBhxh(dto.getThoiGianKetThucBhxh());
        if (dto.getGhiChu() != null) entity.setGhiChu(dto.getGhiChu());
        if (dto.getPhuongThucKyKet() != null) entity.setPhuongThucKyKet(dto.getPhuongThucKyKet());
    }

    public static ContractResponse toResponse(LaborContract entity) {
        if (entity == null) {
            return null;
        }
        ContractResponse resp = new ContractResponse();
        resp.setContractUuid(entity.getUuid() != null ? entity.getUuid().toString() : null);
        resp.setEnterpriseUuid(entity.getDoanhNghiepUuid() != null ? entity.getDoanhNghiepUuid().toString() : null);
        resp.setTrangThai(entity.getTrangThai());
        resp.setThongTinNld(toEmployeeResponse(entity.getThongTinNld()));
        resp.setThongTinHopDong(toContractInfoResponse(entity.getThongTinHopDong()));
        return resp;
    }

    private static ContractResponse.EmployeeInfoResponse toEmployeeResponse(EmployeeInfo info) {
        if (info == null) {
            return null;
        }
        ContractResponse.EmployeeInfoResponse resp = new ContractResponse.EmployeeInfoResponse();
        resp.setHoTen(info.getHoTen());
        resp.setMaSoBhxh(info.getMaSoBhxh());
        resp.setNgaySinh(info.getNgaySinh());
        resp.setGioiTinh(info.getGioiTinh());
        resp.setMaDinhDanh(info.getMaDinhDanh());
        resp.setEmail(info.getEmail() != null ? info.getEmail().toString() : null);
        resp.setDienThoai(info.getDienThoai());
        return resp;
    }

    private static ContractResponse.ContractInfoResponse toContractInfoResponse(ContractInfo info) {
        if (info == null) {
            return null;
        }
        ContractResponse.ContractInfoResponse resp = new ContractResponse.ContractInfoResponse();
        resp.setCapBac(info.getCapBac());
        resp.setViTriLamViec(info.getViTriLamViec());
        resp.setMucLuong(info.getMucLuong());
        resp.setPhuCapChucVu(info.getPhuCapChucVu());
        resp.setPhuCapThamNien(info.getPhuCapThamNien());
        resp.setThamNienNghe(info.getThamNienNghe());
        resp.setPhuCapLuong(info.getPhuCapLuong());
        resp.setCacKhoanBoSung(info.getCacKhoanBoSung());
        resp.setDocHaiTuNgay(info.getDocHaiTuNgay());
        resp.setDocHaiDenNgay(info.getDocHaiDenNgay());
        resp.setLoaiHopDong(info.getLoaiHopDong());
        resp.setMaSoHopDong(info.getMaSoHopDong());
        resp.setLoaiBaoCao(info.getLoaiBaoCao());
        resp.setUuidHopDong(info.getUuidHopDong());
        resp.setNgayHieuLuc(info.getNgayHieuLuc());
        resp.setNgayHetHieuLuc(info.getNgayHetHieuLuc());
        resp.setThoiGianBatDauBhxh(info.getThoiGianBatDauBhxh());
        resp.setThoiGianKetThucBhxh(info.getThoiGianKetThucBhxh());
        resp.setGhiChu(info.getGhiChu());
        resp.setPhuongThucKyKet(info.getPhuongThucKyKet());
        return resp;
    }
}
