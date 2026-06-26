package com.example.hdld.application.dto.response;

import com.example.hdld.application.dto.RootResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response DTO for POST /hdld/KiemTraTrangThaiGiaoDich.
 *
 * <p>Returns at the root the {@code ma_giao_dich} of this check call and a
 * {@code data} array describing the outcome of the queried transaction.
 */
public class CheckTransactionResponse extends RootResponse {

    @JsonProperty("ma_giao_dich")
    private String maGiaoDich;

    @JsonProperty("data")
    private List<TransactionDetail> data;

    public CheckTransactionResponse() {
    }

    public CheckTransactionResponse(String maGiaoDich, List<TransactionDetail> data) {
        super(200, "E00", "Thành công");
        this.maGiaoDich = maGiaoDich;
        this.data = data;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public List<TransactionDetail> getData() {
        return data;
    }

    public void setData(List<TransactionDetail> data) {
        this.data = data;
    }

    /** One processed-transaction record (an element of the {@code data} array). */
    public static class TransactionDetail {

        @JsonProperty("ma_giao_dich")
        private String maGiaoDich;

        @JsonProperty("ma_loi")
        private String maLoi;

        @JsonProperty("trang_thai")
        private String trangThai;

        @JsonProperty("ket_qua_xu_ly")
        private String ketQuaXuLy;

        @JsonProperty("hopdong_uuid")
        private String hopdongUuid;

        @JsonProperty("ma_so_hop_dong")
        private String maSoHopDong;

        public TransactionDetail() {
        }

        public TransactionDetail(String maGiaoDich, String maLoi, String trangThai,
                                 String ketQuaXuLy, String hopdongUuid, String maSoHopDong) {
            this.maGiaoDich = maGiaoDich;
            this.maLoi = maLoi;
            this.trangThai = trangThai;
            this.ketQuaXuLy = ketQuaXuLy;
            this.hopdongUuid = hopdongUuid;
            this.maSoHopDong = maSoHopDong;
        }

        public String getMaGiaoDich() { return maGiaoDich; }
        public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }

        public String getMaLoi() { return maLoi; }
        public void setMaLoi(String maLoi) { this.maLoi = maLoi; }

        public String getTrangThai() { return trangThai; }
        public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

        public String getKetQuaXuLy() { return ketQuaXuLy; }
        public void setKetQuaXuLy(String ketQuaXuLy) { this.ketQuaXuLy = ketQuaXuLy; }

        public String getHopdongUuid() { return hopdongUuid; }
        public void setHopdongUuid(String hopdongUuid) { this.hopdongUuid = hopdongUuid; }

        public String getMaSoHopDong() { return maSoHopDong; }
        public void setMaSoHopDong(String maSoHopDong) { this.maSoHopDong = maSoHopDong; }
    }
}
