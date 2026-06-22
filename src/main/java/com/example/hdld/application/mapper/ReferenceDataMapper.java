package com.example.hdld.application.mapper;

import com.example.hdld.application.dto.response.*;
import com.example.hdld.domain.entity.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps reference data domain entities to response DTOs.
 */
public class ReferenceDataMapper {

    private ReferenceDataMapper() {
    }

    public static ProvinceResponse toResponse(Province entity) {
        if (entity == null) {
            return null;
        }
        return new ProvinceResponse(entity.getMa(), entity.getTen(), entity.getTrangThai());
    }

    public static List<ProvinceResponse> toProvinceList(List<Province> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(ReferenceDataMapper::toResponse).collect(Collectors.toList());
    }

    public static WardResponse toResponse(Ward entity) {
        if (entity == null) {
            return null;
        }
        return new WardResponse(
                entity.getMa(),
                entity.getTen(),
                entity.getTinhThanhId(),
                entity.getTinhThanhString(),
                entity.getTrangThai()
        );
    }

    public static List<WardResponse> toWardList(List<Ward> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(ReferenceDataMapper::toResponse).collect(Collectors.toList());
    }

    public static SectorResponse toResponse(BusinessSector entity) {
        if (entity == null) {
            return null;
        }
        return new SectorResponse(entity.getMa(), entity.getTen(), entity.getTrangThai());
    }

    public static List<SectorResponse> toSectorList(List<BusinessSector> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(ReferenceDataMapper::toResponse).collect(Collectors.toList());
    }

    public static IndustryResponse toResponse(Industry entity) {
        if (entity == null) {
            return null;
        }
        return new IndustryResponse(
                entity.getMa(),
                entity.getTen(),
                entity.getParentId(),
                entity.getParentString(),
                entity.getLinhVucKinhDoanhId(),
                entity.getLinhVucKinhDoanhString(),
                entity.getTrangThai()
        );
    }

    public static List<IndustryResponse> toIndustryList(List<Industry> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(ReferenceDataMapper::toResponse).collect(Collectors.toList());
    }

    public static EnterpriseTypeResponse toResponse(EnterpriseType entity) {
        if (entity == null) {
            return null;
        }
        return new EnterpriseTypeResponse(
                entity.getMa(),
                entity.getTen(),
                entity.getLoai(),
                entity.getTrangThai(),
                entity.getTenTiengAnh()
        );
    }

    public static List<EnterpriseTypeResponse> toEnterpriseTypeList(List<EnterpriseType> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(ReferenceDataMapper::toResponse).collect(Collectors.toList());
    }
}
