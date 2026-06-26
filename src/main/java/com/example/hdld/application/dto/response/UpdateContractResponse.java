package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/CapNhatHopDongLaoDong.
 * Returns the contract uuid and the generated transaction id at the root.
 */
public class UpdateContractResponse extends RootResponse {

    @JsonProperty("hopdong_uuid")
    private String hopdongUuid;

    @JsonProperty("ma_giao_dich")
    private String maGiaoDich;

    public UpdateContractResponse() {
    }

    public UpdateContractResponse(String hopdongUuid, String maGiaoDich) {
        super(200, "E00", "Thành công");
        this.hopdongUuid = hopdongUuid;
        this.maGiaoDich = maGiaoDich;
    }

    public String getHopdongUuid() { return hopdongUuid; }
    public void setHopdongUuid(String hopdongUuid) { this.hopdongUuid = hopdongUuid; }

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }
}
