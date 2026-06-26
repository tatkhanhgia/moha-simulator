package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/UploadFileHopDongLaoDong.
 * Returns the transaction id and the stored file's uuid at the root.
 */
public class UploadFileResponse extends RootResponse {

    @JsonProperty("ma_giao_dich")
    private String maGiaoDich;

    @JsonProperty("uuid_file")
    private String uuidFile;

    public UploadFileResponse() {
    }

    public UploadFileResponse(String maGiaoDich, String uuidFile) {
        super(200, "E00", "Thành công");
        this.maGiaoDich = maGiaoDich;
        this.uuidFile = uuidFile;
    }

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }

    public String getUuidFile() { return uuidFile; }
    public void setUuidFile(String uuidFile) { this.uuidFile = uuidFile; }
}
