package com.example.hdld.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base response whose fields are emitted at the JSON root, matching the official
 * HDLD platform contract (see docs/pdf_extract.txt).
 *
 * <p>The platform never wraps payloads in a {@code data} envelope for the common
 * fields: {@code status} (numeric), {@code error_code} and {@code message} always
 * sit at the root. Endpoint-specific responses extend this class and add their own
 * root-level fields. Because {@code spring.jackson.default-property-inclusion=non_null}
 * is configured, {@code null} fields are omitted; {@code status} is a primitive int so
 * it is always serialized.
 */
public class RootResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("message")
    private String message;

    public RootResponse() {
    }

    public RootResponse(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    /** Standard success envelope: status 200 / E00. */
    public static RootResponse success(String message) {
        return new RootResponse(200, "E00", message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
