package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Request DTO for POST /hdld/ThemMoiTheoLoHopDongLaoDong
 */
public class BulkCreateContractRequest {

    @NotNull
    @Size(max = 100, message = "Maximum 100 contracts per batch")
    @JsonProperty("contracts")
    private List<@Valid CreateContractRequest> contracts;

    public BulkCreateContractRequest() {
    }

    public List<CreateContractRequest> getContracts() {
        return contracts;
    }

    public void setContracts(List<CreateContractRequest> contracts) {
        this.contracts = contracts;
    }
}
