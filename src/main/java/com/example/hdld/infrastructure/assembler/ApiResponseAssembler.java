package com.example.hdld.infrastructure.assembler;

import com.example.hdld.application.dto.ApiResponse;

public class ApiResponseAssembler {

    private ApiResponseAssembler() {
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.success(data);
    }

    public static <T> ApiResponse<T> error(String errorCode, String message) {
        return ApiResponse.error(errorCode, message);
    }
}
