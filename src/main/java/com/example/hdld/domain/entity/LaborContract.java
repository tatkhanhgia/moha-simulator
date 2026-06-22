package com.example.hdld.domain.entity;

import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.EnterpriseUuid;

/**
 * Aggregate root representing a labor contract (hop dong lao dong).
 */
public class LaborContract {

    private ContractUuid uuid;
    private EnterpriseUuid doanhNghiepUuid;
    private EmployeeInfo thongTinNld;
    private ContractInfo thongTinHopDong;
    private String trangThai;

    public LaborContract() {
    }

    public LaborContract(ContractUuid uuid, EnterpriseUuid doanhNghiepUuid,
                         EmployeeInfo thongTinNld, ContractInfo thongTinHopDong, String trangThai) {
        this.uuid = uuid;
        this.doanhNghiepUuid = doanhNghiepUuid;
        this.thongTinNld = thongTinNld;
        this.thongTinHopDong = thongTinHopDong;
        this.trangThai = trangThai;
    }

    /**
     * Updates the contract status if the transition is valid.
     */
    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Status must not be blank");
        }
        this.trangThai = newStatus;
    }

    public ContractUuid getUuid() {
        return uuid;
    }

    public void setUuid(ContractUuid uuid) {
        this.uuid = uuid;
    }

    public EnterpriseUuid getDoanhNghiepUuid() {
        return doanhNghiepUuid;
    }

    public void setDoanhNghiepUuid(EnterpriseUuid doanhNghiepUuid) {
        this.doanhNghiepUuid = doanhNghiepUuid;
    }

    public EmployeeInfo getThongTinNld() {
        return thongTinNld;
    }

    public void setThongTinNld(EmployeeInfo thongTinNld) {
        this.thongTinNld = thongTinNld;
    }

    public ContractInfo getThongTinHopDong() {
        return thongTinHopDong;
    }

    public void setThongTinHopDong(ContractInfo thongTinHopDong) {
        this.thongTinHopDong = thongTinHopDong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
