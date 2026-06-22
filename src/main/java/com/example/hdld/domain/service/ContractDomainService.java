package com.example.hdld.domain.service;

import com.example.hdld.domain.entity.ContractInfo;
import com.example.hdld.domain.entity.EmployeeInfo;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.exception.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Domain service for labor contract validation rules.
 */
public class ContractDomainService {

    /**
     * Validates contract date ranges: end date must be on or after start date.
     */
    public void validateContractDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new ValidationException("Contract start date is required");
        }
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new ValidationException("Contract end date must be on or after start date");
        }
    }

    /**
     * Validates that salary is positive.
     */
    public void validateSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Salary must be greater than zero");
        }
    }

    /**
     * Validates employee info completeness.
     */
    public void validateEmployeeInfo(EmployeeInfo employeeInfo) {
        if (employeeInfo == null) {
            throw new ValidationException("Employee information is required");
        }
        if (employeeInfo.getHoTen() == null || employeeInfo.getHoTen().isBlank()) {
            throw new ValidationException("Employee full name is required");
        }
        if (employeeInfo.getMaDinhDanh() == null || employeeInfo.getMaDinhDanh().isBlank()) {
            throw new ValidationException("Employee ID number is required");
        }
        if (!employeeInfo.getMaDinhDanh().matches("^\\d{9}$|^\\d{12}$")) {
            throw new ValidationException("Employee ID number must be 9 or 12 digits");
        }
        if (employeeInfo.getNgaySinh() == null) {
            throw new ValidationException("Employee date of birth is required");
        }
        if (employeeInfo.getGioiTinh() == null || employeeInfo.getGioiTinh().isBlank()) {
            throw new ValidationException("Employee gender is required");
        }
    }

    /**
     * Validates a full labor contract.
     */
    public void validateContract(LaborContract contract) {
        if (contract == null) {
            throw new ValidationException("Contract is required");
        }
        validateEmployeeInfo(contract.getThongTinNld());
        ContractInfo info = contract.getThongTinHopDong();
        if (info == null) {
            throw new ValidationException("Contract information is required");
        }
        validateContractDates(info.getNgayHieuLuc(), info.getNgayHetHieuLuc());
        validateSalary(info.getMucLuong());
    }
}
