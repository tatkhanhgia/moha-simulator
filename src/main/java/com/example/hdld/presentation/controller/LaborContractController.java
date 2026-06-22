package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.BulkCreateContractRequest;
import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.request.GetContractRequest;
import com.example.hdld.application.dto.request.UpdateContractRequest;
import com.example.hdld.application.dto.response.BulkContractResponse;
import com.example.hdld.application.dto.response.ContractResponse;
import com.example.hdld.application.usecase.BulkCreateContractUseCase;
import com.example.hdld.application.usecase.CreateContractUseCase;
import com.example.hdld.application.usecase.GetContractUseCase;
import com.example.hdld.application.usecase.UpdateContractUseCase;
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
 * Labor contract management endpoints.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Labor Contract", description = "Labor contract management APIs")
@SecurityRequirement(name = "bearerAuth")
public class LaborContractController {

    private final CreateContractUseCase createUseCase;
    private final BulkCreateContractUseCase bulkCreateUseCase;
    private final UpdateContractUseCase updateUseCase;
    private final GetContractUseCase getUseCase;

    public LaborContractController(CreateContractUseCase createUseCase,
                                   BulkCreateContractUseCase bulkCreateUseCase,
                                   UpdateContractUseCase updateUseCase,
                                   GetContractUseCase getUseCase) {
        this.createUseCase = createUseCase;
        this.bulkCreateUseCase = bulkCreateUseCase;
        this.updateUseCase = updateUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping("/ThemMoiHopDongLaoDong")
    @Operation(summary = "Create new labor contract")
    @ApiResponse(responseCode = "200", description = "Contract created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<ContractResponse>> create(
            @Valid @RequestBody CreateContractRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(createUseCase.execute(request)));
    }

    @PostMapping("/ThemMoiTheoLoHopDongLaoDong")
    @Operation(summary = "Bulk create labor contracts")
    @ApiResponse(responseCode = "200", description = "Contracts processed")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<BulkContractResponse>> bulkCreate(
            @Valid @RequestBody BulkCreateContractRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(bulkCreateUseCase.execute(request)));
    }

    @PostMapping("/CapNhatHopDongLaoDong")
    @Operation(summary = "Update existing labor contract")
    @ApiResponse(responseCode = "200", description = "Contract updated")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<ContractResponse>> update(
            @Valid @RequestBody UpdateContractRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(updateUseCase.execute(request)));
    }

    @PostMapping("/LayThongTinHopDong")
    @Operation(summary = "Get contract information")
    @ApiResponse(responseCode = "200", description = "Contract retrieved")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<ContractResponse>> get(
            @Valid @RequestBody GetContractRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(getUseCase.execute(request)));
    }
}
