package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response DTO for bulk contract creation.
 */
public class BulkContractResponse {

    @JsonProperty("total")
    private int total;

    @JsonProperty("success_count")
    private int successCount;

    @JsonProperty("failure_count")
    private int failureCount;

    @JsonProperty("contracts")
    private List<ContractResponse> contracts;

    @JsonProperty("errors")
    private List<BulkError> errors;

    public BulkContractResponse() {
    }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getFailureCount() { return failureCount; }
    public void setFailureCount(int failureCount) { this.failureCount = failureCount; }

    public List<ContractResponse> getContracts() { return contracts; }
    public void setContracts(List<ContractResponse> contracts) { this.contracts = contracts; }

    public List<BulkError> getErrors() { return errors; }
    public void setErrors(List<BulkError> errors) { this.errors = errors; }

    public static class BulkError {
        @JsonProperty("index")
        private int index;

        @JsonProperty("error_code")
        private String errorCode;

        @JsonProperty("message")
        private String message;

        public BulkError() {
        }

        public BulkError(int index, String errorCode, String message) {
            this.index = index;
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getIndex() { return index; }
        public void setIndex(int index) { this.index = index; }

        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
