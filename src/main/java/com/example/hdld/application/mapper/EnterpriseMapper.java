package com.example.hdld.application.mapper;

import com.example.hdld.application.dto.request.CreateEnterpriseRequest;
import com.example.hdld.application.dto.request.UpdateEnterpriseRequest;
import com.example.hdld.application.dto.response.EnterpriseResponse;
import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.EnterpriseUuid;
import com.example.hdld.domain.valueobject.TaxCode;

/**
 * Maps between Enterprise domain entity and DTOs.
 */
public class EnterpriseMapper {

    private EnterpriseMapper() {
    }

    public static Enterprise toDomain(CreateEnterpriseRequest dto) {
        if (dto == null) {
            return null;
        }
        return new Enterprise(
                new EnterpriseUuid(java.util.UUID.randomUUID()),
                dto.getTenDoanhNghiep(),
                dto.getLoaiHinhDoanhNghiep(),
                dto.getDiaChi(),
                dto.getMaTinh(),
                dto.getMaXa(),
                dto.getNguoiDaiDien(),
                dto.getDienThoai(),
                dto.getFax(),
                dto.getEmail() != null ? new Email(dto.getEmail()) : null,
                dto.getWebsite(),
                new TaxCode(dto.getMaSoThue()),
                dto.getMaLinhVuc(),
                dto.getMaNganhNghe()
        );
    }

    public static void applyUpdate(Enterprise entity, UpdateEnterpriseRequest dto) {
        if (entity == null || dto == null) {
            return;
        }
        if (dto.getTenDoanhNghiep() != null) {
            entity.setTenDoanhNghiep(dto.getTenDoanhNghiep());
        }
        if (dto.getLoaiHinhDoanhNghiep() != null) {
            entity.setLoaiHinhDoanhNghiep(dto.getLoaiHinhDoanhNghiep());
        }
        if (dto.getDiaChi() != null) {
            entity.setDiaChi(dto.getDiaChi());
        }
        if (dto.getMaTinh() != null) {
            entity.setMaTinh(dto.getMaTinh());
        }
        if (dto.getMaXa() != null) {
            entity.setMaXa(dto.getMaXa());
        }
        if (dto.getNguoiDaiDien() != null) {
            entity.setNguoiDaiDien(dto.getNguoiDaiDien());
        }
        if (dto.getDienThoai() != null) {
            entity.setDienThoai(dto.getDienThoai());
        }
        if (dto.getFax() != null) {
            entity.setFax(dto.getFax());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(new Email(dto.getEmail()));
        }
        if (dto.getWebsite() != null) {
            entity.setWebsite(dto.getWebsite());
        }
        if (dto.getMaSoThue() != null) {
            entity.setMaSoThue(new TaxCode(dto.getMaSoThue()));
        }
        if (dto.getMaLinhVuc() != null) {
            entity.setMaLinhVuc(dto.getMaLinhVuc());
        }
        if (dto.getMaNganhNghe() != null) {
            entity.setMaNganhNghe(dto.getMaNganhNghe());
        }
    }

    public static EnterpriseResponse toResponse(Enterprise entity) {
        if (entity == null) {
            return null;
        }
        EnterpriseResponse resp = new EnterpriseResponse();
        resp.setEnterpriseUuid(entity.getUuid() != null ? entity.getUuid().toString() : null);
        resp.setTenDoanhNghiep(entity.getTenDoanhNghiep());
        resp.setLoaiHinhDoanhNghiep(entity.getLoaiHinhDoanhNghiep());
        resp.setDiaChi(entity.getDiaChi());
        resp.setMaTinh(entity.getMaTinh());
        resp.setMaXa(entity.getMaXa());
        resp.setNguoiDaiDien(entity.getNguoiDaiDien());
        resp.setDienThoai(entity.getDienThoai());
        resp.setFax(entity.getFax());
        resp.setEmail(entity.getEmail() != null ? entity.getEmail().toString() : null);
        resp.setWebsite(entity.getWebsite());
        resp.setMaSoThue(entity.getMaSoThue() != null ? entity.getMaSoThue().toString() : null);
        resp.setMaLinhVuc(entity.getMaLinhVuc());
        resp.setMaNganhNghe(entity.getMaNganhNghe());
        return resp;
    }
}
