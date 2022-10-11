package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Buku;
import com.irsan.sinaukoding.entity.Peminjaman;
import com.irsan.sinaukoding.model.PeminjamanSimpanRequest;
import com.irsan.sinaukoding.model.UserData;
import com.irsan.sinaukoding.repository.BukuRepository;
import com.irsan.sinaukoding.repository.PeminjamanRepository;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import com.irsan.sinaukoding.util.Helper;
import com.irsan.sinaukoding.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    @Override
    public BaseResponse<?> pinjamBuku(PeminjamanSimpanRequest pinjamRequest, HttpServletRequest httpServletRequest) {
        UserData userData = SessionUtil.getUserData(httpServletRequest);
        Peminjaman pinjamBuku = new Peminjaman();
        String message = "";
        int batasPeminjaman = Constant.BATAS_PEMINJAMAN;
        int batasBuku = Constant.BATAS_BUKU;
        int getCountAnggota = peminjamanRepository.countAnggotaId(Long.valueOf(pinjamRequest.getAnggotaId()), Constant.STATUS_PINJAM);
        int getCountBuku = peminjamanRepository.countBukuId(Long.valueOf(pinjamRequest.getAnggotaId()), Long.valueOf(pinjamRequest.getBukuId()), Constant.STATUS_PINJAM);
        Buku buku = Optional.of(bukuRepository.findById(Long.valueOf(pinjamRequest.getBukuId()))).get().orElseThrow();
        if (buku.getJumlah() <= 0) {
            pinjamBuku = null;
            message = "Buku habis, tidak bisa melakukan peminjaman";
        } else {
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
                buku.setJumlah(buku.getJumlah() - 1);
                bukuRepository.save(buku);
                message = "Berhasil melakukan peminjaman";
            }
        }
        return BaseResponse.ok(message, pinjamBuku);
    }

    @Override
    public BaseResponse<?> trackPeminjamanSelesai(Long anggotaId) {
        MultiValueMap<Long, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<Long, Object> resMap = new HashMap<>();
        Set<Long> keySet = new HashSet<>();
        List<Peminjaman> allById = peminjamanRepository.findAllByAnggotaIdAndStatus(anggotaId, Constant.STATUS_SELESAI);
        if (Helper.isNullOrEmpty(allById)) {
            multiValueMap.add(anggotaId, "Buku dengan status SELESAI tidak ada");
        } else {
            for (Peminjaman singleId : allById) {
                multiValueMap.add(singleId.getAnggotaId(), getNamaBuku(singleId.getBukuId()));
            }
            keySet = multiValueMap.keySet();
            for (Long key : keySet) {
                resMap.put(key, arrayToString(Objects.requireNonNull(multiValueMap.get(key))));
            }
        }
        return BaseResponse.ok(resMap);
    }

    @Override
    public BaseResponse<?> trackPeminjamanPinjam(Long anggotaId) {
        MultiValueMap<Long, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<Long, Object> resMap = new HashMap<>();
        Set<Long> keySet = new HashSet<>();
        List<Peminjaman> allById = peminjamanRepository.findAllByAnggotaIdAndStatus(anggotaId, Constant.STATUS_PINJAM);
        if (Helper.isNullOrEmpty(allById)) {
            multiValueMap.add(anggotaId, "Buku dengan status PINJAM tidak ada");
        } else {
            for (Peminjaman singleId : allById) {
                multiValueMap.add(singleId.getAnggotaId(), getNamaBuku(singleId.getBukuId()));
            }
            keySet = multiValueMap.keySet();
            for (Long key : keySet) {
                resMap.put(key, arrayToString(Objects.requireNonNull(multiValueMap.get(key))));
            }
        }
        return BaseResponse.ok(resMap);
    }

    @Override
    public BaseResponse<?> trackPeminjaman() {
        MultiValueMap<Long, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<Long, Object> resMap = new HashMap<>();
        Set<Long> keySet = new HashSet<>();
        List<Peminjaman> allPeminjaman = peminjamanRepository.findAll();
        for (Peminjaman singlePeminjaman : allPeminjaman) {
            multiValueMap.add(singlePeminjaman.getAnggotaId(), getNamaBuku(singlePeminjaman.getBukuId()));
        }
        keySet = multiValueMap.keySet();
        for (Long key : keySet) {
            resMap.put(key, arrayToString(Objects.requireNonNull(multiValueMap.get(key))));
        }
        return BaseResponse.ok(resMap);
    }

    private String getNamaBuku(Long bukuId) {
        Buku buku = Optional.of(bukuRepository.findById(bukuId)).get().orElseThrow();
        return buku.getJudulBuku();
    }

    private String arrayToString(List<String> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
