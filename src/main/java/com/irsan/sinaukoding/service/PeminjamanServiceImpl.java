package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Peminjaman;
import com.irsan.sinaukoding.model.PeminjamanSimpanRequest;
import com.irsan.sinaukoding.model.UserData;
import com.irsan.sinaukoding.repository.PeminjamanRepository;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import com.irsan.sinaukoding.util.Helper;
import com.irsan.sinaukoding.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Override
    public BaseResponse<?> pinjamBuku(PeminjamanSimpanRequest pinjamRequest, HttpServletRequest httpServletRequest) {
        UserData userData = SessionUtil.getUserData(httpServletRequest);
        Peminjaman pinjamBuku = new Peminjaman();
        String message = "";
        int batasPeminjaman = Constant.BATAS_PEMINJAMAN;
        int batasBuku = Constant.BATAS_BUKU;
        int getCountAnggota = peminjamanRepository.countAnggotaId(Long.valueOf(pinjamRequest.getAnggotaId()), Constant.STATUS_PINJAM);
        int getCountBuku = peminjamanRepository.countBukuId(Long.valueOf(pinjamRequest.getAnggotaId()), Long.valueOf(pinjamRequest.getBukuId()), Constant.STATUS_PINJAM);
        if (getCountAnggota >= batasPeminjaman) {
            pinjamBuku = null;
            message = "Peminjaman buku lebih dari " + batasPeminjaman + " kali dengan ID Anggota yang sama";
        } else if (getCountBuku >= batasBuku) {
            pinjamBuku = null;
            message = "Peminjaman buku yang sama tidak boleh lebih dari " + batasBuku + " kali";
        } else {
            pinjamBuku = peminjamanRepository.save(Peminjaman.builder()
                    .bukuId(Long.valueOf(pinjamRequest.getBukuId()))
                    .tglPinjam(Helper.currentDate())
                    .tglKembali(Helper.dynamicDate(Helper.currentDate(), Constant.LONG_DAYS))
                    .updatedAt(Helper.currentDate())
                    .anggotaId(Long.valueOf(pinjamRequest.getAnggotaId()))
                    .petugasId(userData.getUserId())
                    .status(Constant.STATUS_PINJAM)
                    .build());
            message = "Berhasil melakukan peminjaman";
        }
        return BaseResponse.ok(message, pinjamBuku);
    }

    @Override
    public BaseResponse<?> updatePinjamBuku(PeminjamanSimpanRequest pinjamRequest) {
        return null;
    }
}
