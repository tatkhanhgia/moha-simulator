package com.example.hdld.application.mapper;

import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.response.GetContractResponse;
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

    /** Flattens a contract into the {@code data} object returned by /hdld/ThongTinHopDong. */
    public static GetContractResponse.ContractDetail toDetail(LaborContract entity) {
        if (entity == null) {
            return null;
        }
        GetContractResponse.ContractDetail d = new GetContractResponse.ContractDetail();
        EmployeeInfo nld = entity.getThongTinNld();
        if (nld != null) {
            d.setHoTen(nld.getHoTen());
            d.setMaSoBhxh(nld.getMaSoBhxh());
            d.setNgaySinh(nld.getNgaySinh());
            d.setGioiTinh(nld.getGioiTinh());
            d.setMaDinhDanh(nld.getMaDinhDanh());
            d.setEmail(nld.getEmail() != null ? nld.getEmail().toString() : null);
            d.setDienThoai(nld.getDienThoai());
        }
        ContractInfo hd = entity.getThongTinHopDong();
        if (hd != null) {
            d.setCapBac(hd.getCapBac());
            d.setViTriLamViec(hd.getViTriLamViec());
            d.setMucLuong(hd.getMucLuong());
            d.setPhuCapChucVu(hd.getPhuCapChucVu());
            d.setPhuCapThamNien(hd.getPhuCapThamNien());
            d.setThamNienNghe(hd.getThamNienNghe());
            d.setPhuCapLuong(hd.getPhuCapLuong());
            d.setCacKhoanBoSung(hd.getCacKhoanBoSung());
            d.setDocHaiTuNgay(hd.getDocHaiTuNgay());
            d.setDocHaiDenNgay(hd.getDocHaiDenNgay());
            d.setLoaiHopDong(hd.getLoaiHopDong());
            d.setMaSoHopDong(hd.getMaSoHopDong());
            d.setLoaiBaoCao(hd.getLoaiBaoCao());
            d.setNgayHieuLuc(hd.getNgayHieuLuc());
            d.setThoiGianBatDauBhxh(hd.getThoiGianBatDauBhxh());
            d.setThoiGianKetThucBhxh(hd.getThoiGianKetThucBhxh());
            d.setGhiChu(hd.getGhiChu());
            d.setPhuongThucKyKet(hd.getPhuongThucKyKet());
        }
        d.setUuidHopDong(entity.getUuid() != null ? entity.getUuid().toString() : null);
        return d;
    }
}
