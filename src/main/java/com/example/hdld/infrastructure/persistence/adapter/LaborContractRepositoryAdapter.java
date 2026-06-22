package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.ContractInfo;
import com.example.hdld.domain.entity.EmployeeInfo;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.infrastructure.persistence.entity.LaborContractJpa;
import com.example.hdld.infrastructure.persistence.repository.LaborContractJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class LaborContractRepositoryAdapter implements LaborContractRepository {

    private final LaborContractJpaRepository jpaRepository;

    public LaborContractRepositoryAdapter(LaborContractJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public LaborContract save(LaborContract contract) {
        LaborContractJpa jpa = toJpa(contract);
        LaborContractJpa saved = jpaRepository.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<LaborContract> findById(ContractUuid id) {
        return jpaRepository.findById(id.getValue()).map(this::toDomain);
    }

    @Override
    public List<LaborContract> findByEnterpriseId(EnterpriseUuid enterpriseId) {
        return jpaRepository.findByEnterpriseUuid(enterpriseId.getValue())
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<LaborContract> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(ContractUuid id) {
        jpaRepository.deleteById(id.getValue());
    }

    private LaborContractJpa toJpa(LaborContract domain) {
        LaborContractJpa jpa = new LaborContractJpa();
        jpa.setUuid(domain.getUuid() != null ? domain.getUuid().getValue() : UUID.randomUUID());
        jpa.setEnterpriseUuid(domain.getDoanhNghiepUuid() != null ? domain.getDoanhNghiepUuid().getValue() : null);
        jpa.setStatus(domain.getTrangThai());

        EmployeeInfo ei = domain.getThongTinNld();
        if (ei != null) {
            jpa.setEmployeeName(ei.getHoTen());
            jpa.setEmployeeBhxh(ei.getMaSoBhxh());
            jpa.setEmployeeDob(ei.getNgaySinh());
            jpa.setEmployeeGender(ei.getGioiTinh());
            jpa.setEmployeeIdNumber(ei.getMaDinhDanh());
            jpa.setEmployeeEmail(ei.getEmail() != null ? ei.getEmail().getValue() : null);
            jpa.setEmployeePhone(ei.getDienThoai());
        }

        ContractInfo ci = domain.getThongTinHopDong();
        if (ci != null) {
            jpa.setCapBac(ci.getCapBac());
            jpa.setViTriLamViec(ci.getViTriLamViec());
            jpa.setMucLuong(ci.getMucLuong());
            jpa.setPhuCapChucVu(ci.getPhuCapChucVu());
            jpa.setPhuCapThamNien(ci.getPhuCapThamNien());
            jpa.setThamNienNghe(ci.getThamNienNghe());
            jpa.setPhuCapLuong(ci.getPhuCapLuong());
            jpa.setCacKhoanBoSung(ci.getCacKhoanBoSung());
            jpa.setDocHaiTuNgay(ci.getDocHaiTuNgay());
            jpa.setDocHaiDenNgay(ci.getDocHaiDenNgay());
            jpa.setLoaiHopDong(ci.getLoaiHopDong());
            jpa.setMaSoHopDong(ci.getMaSoHopDong());
            jpa.setLoaiBaoCao(ci.getLoaiBaoCao());
            jpa.setUuidHopDong(ci.getUuidHopDong());
            jpa.setNgayHieuLuc(ci.getNgayHieuLuc());
            jpa.setNgayHetHieuLuc(ci.getNgayHetHieuLuc());
            jpa.setThoiGianBatDauBhxh(ci.getThoiGianBatDauBhxh());
            jpa.setThoiGianKetThucBhxh(ci.getThoiGianKetThucBhxh());
            jpa.setGhiChu(ci.getGhiChu());
            jpa.setPhuongThucKyKet(ci.getPhuongThucKyKet());
        }

        return jpa;
    }

    private LaborContract toDomain(LaborContractJpa jpa) {
        EmployeeInfo ei = new EmployeeInfo(
                jpa.getEmployeeName(),
                jpa.getEmployeeBhxh(),
                jpa.getEmployeeDob(),
                jpa.getEmployeeGender(),
                jpa.getEmployeeIdNumber(),
                jpa.getEmployeeEmail() != null ? new Email(jpa.getEmployeeEmail()) : null,
                jpa.getEmployeePhone()
        );

        ContractInfo ci = new ContractInfo();
        ci.setCapBac(jpa.getCapBac());
        ci.setViTriLamViec(jpa.getViTriLamViec());
        ci.setMucLuong(jpa.getMucLuong());
        ci.setPhuCapChucVu(jpa.getPhuCapChucVu());
        ci.setPhuCapThamNien(jpa.getPhuCapThamNien());
        ci.setThamNienNghe(jpa.getThamNienNghe());
        ci.setPhuCapLuong(jpa.getPhuCapLuong());
        ci.setCacKhoanBoSung(jpa.getCacKhoanBoSung());
        ci.setDocHaiTuNgay(jpa.getDocHaiTuNgay());
        ci.setDocHaiDenNgay(jpa.getDocHaiDenNgay());
        ci.setLoaiHopDong(jpa.getLoaiHopDong());
        ci.setMaSoHopDong(jpa.getMaSoHopDong());
        ci.setLoaiBaoCao(jpa.getLoaiBaoCao());
        ci.setUuidHopDong(jpa.getUuidHopDong());
        ci.setNgayHieuLuc(jpa.getNgayHieuLuc());
        ci.setNgayHetHieuLuc(jpa.getNgayHetHieuLuc());
        ci.setThoiGianBatDauBhxh(jpa.getThoiGianBatDauBhxh());
        ci.setThoiGianKetThucBhxh(jpa.getThoiGianKetThucBhxh());
        ci.setGhiChu(jpa.getGhiChu());
        ci.setPhuongThucKyKet(jpa.getPhuongThucKyKet());

        return new LaborContract(
                new ContractUuid(jpa.getUuid()),
                jpa.getEnterpriseUuid() != null ? new EnterpriseUuid(jpa.getEnterpriseUuid()) : null,
                ei,
                ci,
                jpa.getStatus()
        );
    }
}
