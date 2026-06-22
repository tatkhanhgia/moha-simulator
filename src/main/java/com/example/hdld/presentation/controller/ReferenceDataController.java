package com.example.hdld.presentation.controller;

import com.example.hdld.application.dto.response.EnterpriseTypeResponse;
import com.example.hdld.application.dto.response.IndustryResponse;
import com.example.hdld.application.dto.response.ProvinceResponse;
import com.example.hdld.application.dto.response.SectorResponse;
import com.example.hdld.application.dto.response.WardResponse;
import com.example.hdld.application.usecase.ListBusinessSectorsUseCase;
import com.example.hdld.application.usecase.ListEnterpriseTypesUseCase;
import com.example.hdld.application.usecase.ListIndustriesUseCase;
import com.example.hdld.application.usecase.ListProvincesUseCase;
import com.example.hdld.application.usecase.ListWardsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Reference data lookup endpoints.
 */
@RestController
@RequestMapping("/hdld")
@Tag(name = "Reference Data", description = "Reference data lookup APIs")
@SecurityRequirement(name = "bearerAuth")
public class ReferenceDataController {

    private final ListProvincesUseCase listProvincesUseCase;
    private final ListWardsUseCase listWardsUseCase;
    private final ListBusinessSectorsUseCase listBusinessSectorsUseCase;
    private final ListIndustriesUseCase listIndustriesUseCase;
    private final ListEnterpriseTypesUseCase listEnterpriseTypesUseCase;

    public ReferenceDataController(ListProvincesUseCase listProvincesUseCase,
                                   ListWardsUseCase listWardsUseCase,
                                   ListBusinessSectorsUseCase listBusinessSectorsUseCase,
                                   ListIndustriesUseCase listIndustriesUseCase,
                                   ListEnterpriseTypesUseCase listEnterpriseTypesUseCase) {
        this.listProvincesUseCase = listProvincesUseCase;
        this.listWardsUseCase = listWardsUseCase;
        this.listBusinessSectorsUseCase = listBusinessSectorsUseCase;
        this.listIndustriesUseCase = listIndustriesUseCase;
        this.listEnterpriseTypesUseCase = listEnterpriseTypesUseCase;
    }

    @GetMapping("/tinhthanhpho")
    @Operation(summary = "List all provinces/cities")
    @ApiResponse(responseCode = "200", description = "List retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<List<ProvinceResponse>>> listProvinces() {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(listProvincesUseCase.execute()));
    }

    @GetMapping("/xaphuong")
    @Operation(summary = "List wards/communes")
    @ApiResponse(responseCode = "200", description = "List retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<List<WardResponse>>> listWards(
            @RequestParam(name = "province_code", required = false) String provinceCode) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(listWardsUseCase.execute(provinceCode)));
    }

    @GetMapping("/linhvuckinhdoanh")
    @Operation(summary = "List business sectors")
    @ApiResponse(responseCode = "200", description = "List retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<List<SectorResponse>>> listBusinessSectors() {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(listBusinessSectorsUseCase.execute()));
    }

    @GetMapping("/nganhnghe")
    @Operation(summary = "List industries")
    @ApiResponse(responseCode = "200", description = "List retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<List<IndustryResponse>>> listIndustries() {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(listIndustriesUseCase.execute()));
    }

    @GetMapping("/danhmuc")
    @Operation(summary = "List categories by type")
    @ApiResponse(responseCode = "200", description = "List retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<com.example.hdld.application.dto.ApiResponse<List<EnterpriseTypeResponse>>> listCategories(
            @RequestParam(name = "loai") String loai) {
        return ResponseEntity.ok(com.example.hdld.application.dto.ApiResponse.success(listEnterpriseTypesUseCase.execute(loai)));
    }
}
