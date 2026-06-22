package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.request.DeleteAttachmentRequest;
import com.example.hdld.application.dto.request.UploadAttachmentRequest;
import com.example.hdld.application.dto.response.AttachmentResponse;
import com.example.hdld.application.usecase.DeleteAttachmentUseCase;
import com.example.hdld.application.usecase.UploadAttachmentUseCase;
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
 * Attachment management endpoints.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Attachment", description = "Contract attachment file APIs")
@SecurityRequirement(name = "bearerAuth")
public class AttachmentController {

    private final UploadAttachmentUseCase uploadUseCase;
    private final DeleteAttachmentUseCase deleteUseCase;

    public AttachmentController(UploadAttachmentUseCase uploadUseCase,
                                DeleteAttachmentUseCase deleteUseCase) {
        this.uploadUseCase = uploadUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping("/UploadFileHopDong")
    @Operation(summary = "Upload contract attachment file")
    @ApiResponse(responseCode = "200", description = "File uploaded")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<AttachmentResponse>> upload(
            @Valid @RequestBody UploadAttachmentRequest request) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(uploadUseCase.execute(request)));
    }

    @PostMapping("/XoaFileHopDong")
    @Operation(summary = "Delete contract attachment file")
    @ApiResponse(responseCode = "200", description = "File deleted")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Attachment not found")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<Void>> delete(
            @Valid @RequestBody DeleteAttachmentRequest request) {
        deleteUseCase.execute(request);
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success("File deleted successfully", null));
    }
}
