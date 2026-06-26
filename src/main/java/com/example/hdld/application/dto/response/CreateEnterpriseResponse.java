package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/ThemMoiDoanhNghiep.
 *
 * <p>Per the official contract the enterprise UUID is returned at the root under the
 * key {@code Doanhnghiep_uuid} (note the capital {@code D} — the update endpoint uses
 * the lowercase {@code doanhnghiep_uuid}).
 */
public class CreateEnterpriseResponse extends RootResponse {

    @JsonProperty("Doanhnghiep_uuid")
    private String doanhnghiepUuid;

    public CreateEnterpriseResponse() {
    }

    public CreateEnterpriseResponse(String doanhnghiepUuid) {
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
