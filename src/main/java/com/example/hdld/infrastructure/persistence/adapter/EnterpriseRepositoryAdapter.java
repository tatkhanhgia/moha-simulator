package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.domain.valueobject.TaxCode;
import com.example.hdld.infrastructure.persistence.entity.EnterpriseJpa;
import com.example.hdld.infrastructure.persistence.repository.EnterpriseJpaRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EnterpriseRepositoryAdapter implements EnterpriseRepository {

    private final EnterpriseJpaRepository jpaRepository;

    public EnterpriseRepositoryAdapter(EnterpriseJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Enterprise save(Enterprise enterprise) {
        EnterpriseJpa jpa = toJpa(enterprise);
        if (jpa.getCreatedAt() == null) {
            jpa.setCreatedAt(Instant.now());
        }
        jpa.setUpdatedAt(Instant.now());
        EnterpriseJpa saved = jpaRepository.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<Enterprise> findById(EnterpriseUuid id) {
        return jpaRepository.findById(id.getValue()).map(this::toDomain);
    }

    @Override
    public Optional<Enterprise> findByMaSoThue(String maSoThue) {
        return jpaRepository.findByMaSoThue(maSoThue).map(this::toDomain);
    }

    @Override
    public List<Enterprise> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(EnterpriseUuid id) {
        jpaRepository.deleteById(id.getValue());
    }

    private EnterpriseJpa toJpa(Enterprise domain) {
        EnterpriseJpa jpa = new EnterpriseJpa();
        jpa.setUuid(domain.getUuid() != null ? domain.getUuid().getValue() : UUID.randomUUID());
        jpa.setTenDoanhNghiep(domain.getTenDoanhNghiep());
        jpa.setLoaiHinhDoanhNghiep(domain.getLoaiHinhDoanhNghiep());
        jpa.setDiaChi(domain.getDiaChi());
        jpa.setMaTinh(domain.getMaTinh());
        jpa.setMaXa(domain.getMaXa());
        jpa.setNguoiDaiDien(domain.getNguoiDaiDien());
        jpa.setDienThoai(domain.getDienThoai());
        jpa.setFax(domain.getFax());
        jpa.setEmail(domain.getEmail() != null ? domain.getEmail().getValue() : null);
        jpa.setWebsite(domain.getWebsite());
        jpa.setMaSoThue(domain.getMaSoThue() != null ? domain.getMaSoThue().getValue() : null);
        jpa.setMaLinhVuc(domain.getMaLinhVuc());
        jpa.setMaNganhNghe(domain.getMaNganhNghe());
        return jpa;
    }

    private Enterprise toDomain(EnterpriseJpa jpa) {
        return new Enterprise(
                new EnterpriseUuid(jpa.getUuid()),
                jpa.getTenDoanhNghiep(),
                jpa.getLoaiHinhDoanhNghiep(),
                jpa.getDiaChi(),
                jpa.getMaTinh(),
                jpa.getMaXa(),
                jpa.getNguoiDaiDien(),
                jpa.getDienThoai(),
                jpa.getFax(),
                jpa.getEmail() != null ? new Email(jpa.getEmail()) : null,
                jpa.getWebsite(),
                jpa.getMaSoThue() != null ? new TaxCode(jpa.getMaSoThue()) : null,
                jpa.getMaLinhVuc(),
                jpa.getMaNganhNghe()
        );
    }
}
