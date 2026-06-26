package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/QuenMatKhau.
 *
 * <p>This endpoint uses a slightly different root shape than the rest of the
 * platform: {@code status} (numeric), a refreshed {@code token}, and {@code msg}
 * (not {@code message}). There is no {@code error_code} on success.
 */
public class ChangePasswordResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("token")
    private String token;

    @JsonProperty("msg")
    private String msg;

    public ChangePasswordResponse() {
    }

    public ChangePasswordResponse(int status, String token, String msg) {
        this.status = status;
        this.token = token;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
