package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for POST /hdld/ThemMoiHopDongLaoDong and /hdld/ThemMoiTheoLoHopDongLaoDong.
 *
 * <p>The platform acknowledges the write asynchronously: it returns the
 * {@code transaction_id} to poll, with {@code hopdong_uuid} and {@code mahopdong}
 * explicitly {@code null} (the contract is not resolved yet — poll
 * {@code KiemTraTrangThaiGiaoDich}). The two null fields are force-serialized so the
 * shape matches the documented sample exactly.
 */
public class CreateContractResponse extends RootResponse {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("hopdong_uuid")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String hopdongUuid;

    @JsonProperty("mahopdong")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String mahopdong;

    public CreateContractResponse() {
    }

    public CreateContractResponse(String transactionId, String message) {
        super(200, "E00", message);
        this.transactionId = transactionId;
        this.hopdongUuid = null;
        this.mahopdong = null;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getHopdongUuid() {
        return hopdongUuid;
    }

    public void setHopdongUuid(String hopdongUuid) {
        this.hopdongUuid = hopdongUuid;
    }

    public String getMahopdong() {
        return mahopdong;
    }

    public void setMahopdong(String mahopdong) {
        this.mahopdong = mahopdong;
    }
}
