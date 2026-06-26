package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.BulkCreateContractRequest;
import com.example.hdld.application.dto.request.CreateContractRequest;
import com.example.hdld.application.dto.request.GetContractRequest;
import com.example.hdld.application.dto.request.UpdateContractRequest;
import com.example.hdld.application.dto.response.CreateContractResponse;
import com.example.hdld.application.dto.response.GetContractResponse;
import com.example.hdld.application.dto.response.UpdateContractResponse;
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
 * Labor contract management endpoints. Responses follow the HDLD platform's root-level
 * contract (see docs/pdf_extract.txt): writes are acknowledged asynchronously with a
 * transaction id, and the contract detail is read back in a flat {@code data} object.
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
    @ApiResponse(responseCode = "200", description = "Contract accepted")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<CreateContractResponse> create(
            @Valid @RequestBody CreateContractRequest request) {
        return ResponseEntity.ok(createUseCase.execute(request));
    }

    @PostMapping("/ThemMoiTheoLoHopDongLaoDong")
    @Operation(summary = "Bulk create labor contracts")
    @ApiResponse(responseCode = "200", description = "Contracts accepted")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<CreateContractResponse> bulkCreate(
            @Valid @RequestBody BulkCreateContractRequest request) {
        return ResponseEntity.ok(bulkCreateUseCase.execute(request));
    }

    @PostMapping("/CapNhatHopDongLaoDong")
    @Operation(summary = "Update existing labor contract")
    @ApiResponse(responseCode = "200", description = "Contract updated")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    public ResponseEntity<UpdateContractResponse> update(
            @Valid @RequestBody UpdateContractRequest request) {
        return ResponseEntity.ok(updateUseCase.execute(request));
    }

    @PostMapping("/ThongTinHopDong")
    @Operation(summary = "Get contract information")
    @ApiResponse(responseCode = "200", description = "Contract retrieved")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    public ResponseEntity<GetContractResponse> get(
            @Valid @RequestBody GetContractRequest request) {
        return ResponseEntity.ok(getUseCase.execute(request));
    }
}
