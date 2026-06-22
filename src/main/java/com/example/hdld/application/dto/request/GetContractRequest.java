package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for POST /hdld/LayThongTinHopDong
 */
public class GetContractRequest {

    @NotBlank
    @JsonProperty("contract_uuid")
    private String contractUuid;

    public GetContractRequest() {
    }

    public GetContractRequest(String contractUuid) {
        this.contractUuid = contractUuid;
    }

    public String getContractUuid() {
        return contractUuid;
    }

    public void setContractUuid(String contractUuid) {
        this.contractUuid = contractUuid;
    }
}
