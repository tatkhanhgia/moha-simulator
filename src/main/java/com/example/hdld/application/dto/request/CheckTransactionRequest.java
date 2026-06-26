package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for POST /hdld/KiemTraTrangThaiGiaoDich
 */
public class CheckTransactionRequest {

    @NotBlank
    @JsonProperty("ma_giao_dich")
    private String transactionId;

    public CheckTransactionRequest() {
    }

    public CheckTransactionRequest(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
