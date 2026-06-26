package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.CheckTransactionRequest;
import com.example.hdld.application.dto.response.CheckTransactionResponse;
import com.example.hdld.application.usecase.CheckTransactionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transaction status check endpoints.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Transaction", description = "Transaction status APIs")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final CheckTransactionUseCase checkTransactionUseCase;

    public TransactionController(CheckTransactionUseCase checkTransactionUseCase) {
        this.checkTransactionUseCase = checkTransactionUseCase;
    }

    @PostMapping("/KiemTraTrangThaiGiaoDich")
    @Operation(summary = "Check transaction status")
    @ApiResponse(responseCode = "200", description = "Transaction status retrieved")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Transaction not found")
    public ResponseEntity<CheckTransactionResponse> check(
            @Valid @RequestBody CheckTransactionRequest request) {
        return ResponseEntity.ok(checkTransactionUseCase.execute(request));
    }
}
