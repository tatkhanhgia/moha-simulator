package com.example.hdld.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Request DTO for POST /hdld/KiemTraTrangThaiGiaoDich
 */
public class CheckTransactionRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Transaction ID must be alphanumeric")
    @JsonProperty("transaction_id")
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
