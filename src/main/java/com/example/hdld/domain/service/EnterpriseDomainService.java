package com.example.hdld.domain.service;

import com.example.hdld.domain.entity.Enterprise;
import com.example.hdld.domain.exception.ConflictException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.valueobject.Email;
import com.example.hdld.domain.valueobject.TaxCode;

import java.util.regex.Pattern;

/**
 * Domain service for enterprise validation rules.
 */
public class EnterpriseDomainService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseDomainService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    /**
     * Validates that tax code is unique across all enterprises.
     */
    public void validateUniqueTaxCode(TaxCode taxCode) {
        if (enterpriseRepository.findByMaSoThue(taxCode.toString()).isPresent()) {
            throw new ConflictException("Tax code already exists: " + taxCode);
        }
    }

    /**
     * Validates email format.
     */
    public void validateEmailFormat(String email) {
        if (email == null || email.isBlank()) {
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Email format is invalid: " + email);
        }
    }

    /**
     * Validates required enterprise fields.
     */
    public void validateRequiredFields(Enterprise enterprise) {
        if (enterprise.getTenDoanhNghiep() == null || enterprise.getTenDoanhNghiep().isBlank()) {
            throw new ValidationException("Enterprise name is required");
        }
        if (enterprise.getMaSoThue() == null) {
            throw new ValidationException("Tax code is required");
        }
        if (enterprise.getDiaChi() == null || enterprise.getDiaChi().isBlank()) {
            throw new ValidationException("Address is required");
        }
        if (enterprise.getMaTinh() == null || enterprise.getMaTinh().isBlank()) {
            throw new ValidationException("Province code is required");
        }
        if (enterprise.getMaXa() == null || enterprise.getMaXa().isBlank()) {
            throw new ValidationException("Ward code is required");
        }
    }
}
