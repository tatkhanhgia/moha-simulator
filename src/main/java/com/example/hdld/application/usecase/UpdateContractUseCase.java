package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.UpdateContractRequest;
import com.example.hdld.application.dto.request.UpdateContractRequest.ChangeInfo;
import com.example.hdld.application.dto.response.UpdateContractResponse;
import com.example.hdld.domain.entity.ContractInfo;
import com.example.hdld.domain.entity.EmployeeInfo;
import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.entity.Transaction;
import com.example.hdld.domain.exception.NotFoundException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.service.TransactionIdGenerator;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.TransactionId;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Applies a typed change ({@code loai_thay_doi}) to an existing labor contract and
 * records an update transaction. Returns the contract uuid and the transaction id.
 */
public class UpdateContractUseCase {

    private final LaborContractRepository contractRepository;
    private final TransactionRepository transactionRepository;

    public UpdateContractUseCase(LaborContractRepository contractRepository,
                                 TransactionRepository transactionRepository) {
        this.contractRepository = contractRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public UpdateContractResponse execute(UpdateContractRequest request) {
        LaborContract contract = contractRepository.findById(new ContractUuid(request.getUuidHopDong()))
                .orElseThrow(() -> new NotFoundException("Contract not found: " + request.getUuidHopDong()));

        applyChange(contract, request.getLoaiThayDoi(), request.getThongTinThayDoi());
        LaborContract saved = contractRepository.save(contract);

        String hopdongUuid = saved.getUuid() != null ? saved.getUuid().toString() : null;
        String transactionId = TransactionIdGenerator.generate(TransactionIdGenerator.UPDATE);
        Instant now = Instant.now();
        transactionRepository.save(new Transaction(
                new TransactionId(transactionId),
                TransactionIdGenerator.UPDATE,
                "Thành công",
                "Thành công",
                "E00",
                "Thành công",
                hopdongUuid,
                saved.getThongTinHopDong() != null ? saved.getThongTinHopDong().getMaSoHopDong() : null,
                now,
                now
        ));

        return new UpdateContractResponse(hopdongUuid, transactionId);
    }

    private void applyChange(LaborContract contract, Integer loaiThayDoi, ChangeInfo info) {
        if (loaiThayDoi == null || info == null) {
            throw new ValidationException("loai_thay_doi and thong_tin_thay_doi are required");
        }
        ContractInfo hd = contract.getThongTinHopDong();
        EmployeeInfo nld = contract.getThongTinNld();
        switch (loaiThayDoi) {
            case 1 -> { // thay đổi mức lương
                if (info.getMucLuong() != null) hd.setMucLuong(info.getMucLuong());
                if (info.getPhuCapChucVu() != null) hd.setPhuCapChucVu(info.getPhuCapChucVu());
                if (info.getPhuCapThamNien() != null) hd.setPhuCapThamNien(info.getPhuCapThamNien());
                if (info.getThamNienNghe() != null) hd.setThamNienNghe(info.getThamNienNghe());
                if (info.getPhuCapLuong() != null) hd.setPhuCapLuong(info.getPhuCapLuong());
                if (info.getCacKhoanBoSung() != null) hd.setCacKhoanBoSung(info.getCacKhoanBoSung());
                if (info.getNgayHieuLuc() != null) hd.setNgayHieuLuc(info.getNgayHieuLuc());
            }
            case 2 -> { // gia hạn hợp đồng
                if (info.getNgayKetThucMoi() != null) hd.setNgayHetHieuLuc(info.getNgayKetThucMoi());
            }
            case 3 -> { // chấm dứt hợp đồng
                if (info.getNgayChamDutHopDong() != null) hd.setNgayHetHieuLuc(info.getNgayChamDutHopDong());
                contract.setTrangThai("terminated");
            }
            case 4 -> { // thay đổi vị trí làm việc
                if (info.getViTriLamViec() != null) hd.setViTriLamViec(info.getViTriLamViec());
                if (info.getCapBac() != null) hd.setCapBac(info.getCapBac());
            }
            case 5 -> { // cập nhật thông tin BHXH
                if (info.getMaSoBhxh() != null && nld != null) nld.setMaSoBhxh(info.getMaSoBhxh());
                if (info.getThoiGianBatDauBhxh() != null) hd.setThoiGianBatDauBhxh(info.getThoiGianBatDauBhxh());
                if (info.getThoiGianKetThucBhxh() != null) hd.setThoiGianKetThucBhxh(info.getThoiGianKetThucBhxh());
            }
            default -> throw new ValidationException("loai_thay_doi không hợp lệ: " + loaiThayDoi);
        }
    }
}
