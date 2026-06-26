package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/CapNhatDoanhNghiep.
 *
 * <p>Returns the enterprise UUID at the root under {@code doanhnghiep_uuid}
 * (lowercase {@code d}, unlike the create endpoint).
 */
public class UpdateEnterpriseResponse extends RootResponse {

    @JsonProperty("doanhnghiep_uuid")
    private String doanhnghiepUuid;

    public UpdateEnterpriseResponse() {
    }

    public UpdateEnterpriseResponse(String doanhnghiepUuid) {
        super(200, "E00", "Thành công");
        this.doanhnghiepUuid = doanhnghiepUuid;
    }

    public String getDoanhnghiepUuid() {
        return doanhnghiepUuid;
    }

    public void setDoanhnghiepUuid(String doanhnghiepUuid) {
        this.doanhnghiepUuid = doanhnghiepUuid;
    }
}
