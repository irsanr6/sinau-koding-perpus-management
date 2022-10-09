package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Peminjaman;
import com.irsan.sinaukoding.entity.Pengembalian;
import com.irsan.sinaukoding.model.PengembalianSimpanRequest;
import com.irsan.sinaukoding.model.UserData;
import com.irsan.sinaukoding.repository.PeminjamanRepository;
import com.irsan.sinaukoding.repository.PengembalianRepository;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import com.irsan.sinaukoding.util.Helper;
import com.irsan.sinaukoding.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class PengembalianServiceImpl implements PengembalianService {

    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Override
    public BaseResponse<?> kembaliBuku(PengembalianSimpanRequest simpanRequest, HttpServletRequest httpServletRequest) {
        UserData userData = SessionUtil.getUserData(httpServletRequest);
        Pengembalian pengembalian = new Pengembalian();
        String message = "";
        Peminjaman peminjaman = Optional.of(peminjamanRepository.findById(Long.valueOf(simpanRequest.getPeminjamanId()))).get().orElseThrow();
        String status = peminjaman.getStatus();
        if (status == null) {
            peminjaman.setStatus(Constant.STATUS_PINJAM);
            status = peminjaman.getStatus();
        }
        if (status.equals(Constant.STATUS_PINJAM)) {
            pengembalian = pengembalianRepository.save(Pengembalian.builder()
                    .bukuId(peminjaman.getBukuId())
                    .tglPengembalian(Helper.currentDate())
                    .updatedAt(Helper.currentDate())
                    .denda(Helper.getDenda(Helper.currentDate(), peminjaman.getTglPinjam()))
                    .peminjamanId(peminjaman.getPeminjamanId())
                    .anggotaId(peminjaman.getAnggotaId())
                    .petugasId(userData.getUserId())
                    .status(Constant.STATUS_SELESAI)
                    .build());
            message = "Pengembalian berhasil";
        } else {
            pengembalian = null;
            message = "Status buku " + status + "";
        }
        peminjamanRepository.updateStatus(Constant.STATUS_SELESAI, Long.valueOf(simpanRequest.getPeminjamanId()));

        return BaseResponse.ok(message, pengembalian);
    }
}
