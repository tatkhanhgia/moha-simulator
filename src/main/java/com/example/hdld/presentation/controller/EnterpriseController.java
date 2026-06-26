package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.CreateEnterpriseRequest;
import com.example.hdld.application.dto.request.UpdateEnterpriseRequest;
import com.example.hdld.application.dto.response.CreateEnterpriseResponse;
import com.example.hdld.application.dto.response.UpdateEnterpriseResponse;
import com.example.hdld.application.usecase.CreateEnterpriseUseCase;
import com.example.hdld.application.usecase.UpdateEnterpriseUseCase;
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
 * Enterprise management endpoints. Responses follow the HDLD platform's root-level
 * contract (no {@code data} envelope) — see docs/pdf_extract.txt.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Enterprise", description = "Enterprise management APIs")
@SecurityRequirement(name = "bearerAuth")
public class EnterpriseController {

    private final CreateEnterpriseUseCase createUseCase;
    private final UpdateEnterpriseUseCase updateUseCase;

    public EnterpriseController(CreateEnterpriseUseCase createUseCase,
                                UpdateEnterpriseUseCase updateUseCase) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
    }

    @PostMapping("/ThemMoiDoanhNghiep")
    @Operation(summary = "Create new enterprise")
    @ApiResponse(responseCode = "200", description = "Enterprise created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<CreateEnterpriseResponse> create(
            @Valid @RequestBody CreateEnterpriseRequest request) {
        return ResponseEntity.ok(createUseCase.execute(request));
    }

    @PostMapping("/CapNhatDoanhNghiep")
    @Operation(summary = "Update existing enterprise")
    @ApiResponse(responseCode = "200", description = "Enterprise updated")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Enterprise not found")
    public ResponseEntity<UpdateEnterpriseResponse> update(
            @Valid @RequestBody UpdateEnterpriseRequest request) {
        return ResponseEntity.ok(updateUseCase.execute(request));
    }
}
