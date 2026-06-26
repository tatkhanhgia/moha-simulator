package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/login.
 *
 * <p>Per the official contract the token sits at the JSON root alongside
 * {@code status}/{@code error_code}/{@code message} — there is no {@code data}
 * envelope and no {@code expires_in} field.
 */
public class LoginResponse extends RootResponse {

    @JsonProperty("token")
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        super(200, "E00", "Thành công");
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
