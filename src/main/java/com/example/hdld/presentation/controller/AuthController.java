package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.ChangePasswordRequest;
import com.example.hdld.application.dto.request.LoginRequest;
import com.example.hdld.application.dto.response.LoginResponse;
import com.example.hdld.application.usecase.AuthenticateUseCase;
import com.example.hdld.application.usecase.ChangePasswordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication endpoints.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Authentication", description = "Login and password management APIs")
public class AuthController {

    private final AuthenticateUseCase authenticateUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    public AuthController(AuthenticateUseCase authenticateUseCase,
                          ChangePasswordUseCase changePasswordUseCase) {
        this.authenticateUseCase = authenticateUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(authenticateUseCase.execute(request)));
    }

    @PostMapping("/QuenMatKhau")
    @Operation(summary = "Change password", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Password changed")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication) {
        String username = authentication.getName();
        changePasswordUseCase.execute(username, request);
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success("Password changed successfully", null));
    }
}
