package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Buku;
import com.irsan.sinaukoding.model.BukuLihatRequest;
import com.irsan.sinaukoding.model.BukuSimpanRequest;
import com.irsan.sinaukoding.repository.BukuRepository;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Helper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BukuServiceImpl implements BukuService {

    @Autowired
    private BukuRepository bukuRepository;

    @Override
    public BaseResponse<?> getAllBuku(BukuLihatRequest lihatRequest) {
        Pageable pageable = Helper.getPageRequest(lihatRequest.getPageIn(), lihatRequest.getLimit());
        Page<Buku> bukuList = bukuRepository.findAll(findSpec(lihatRequest), pageable);
        return BaseResponse.ok(bukuList);
    }

    private Specification<Buku> findSpec(BukuLihatRequest lihatRequest) {
        return (Root<Buku> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> p = new ArrayList<>();

            if (StringUtils.isNotBlank(lihatRequest.getBukuId())) {
                p.add(cb.equal(root.get("bukuId"), lihatRequest.getBukuId()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getJudulBuku())) {
                p.add(cb.like(root.get("judulBuku"), "%" + lihatRequest.getJudulBuku() + "%"));
            }
            if (StringUtils.isNotBlank(lihatRequest.getTahunTerbit())) {
                p.add(cb.equal(root.get("tahunTerbit"), lihatRequest.getTahunTerbit()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getJumlah())) {
                p.add(cb.equal(root.get("jumlah"), lihatRequest.getJumlah()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getIsbn())) {
                p.add(cb.equal(root.get("isbn"), lihatRequest.getIsbn()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getPengarangId())) {
                p.add(cb.equal(root.get("pengarangId"), lihatRequest.getPengarangId()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getPenerbitId())) {
                p.add(cb.equal(root.get("penerbitId"), lihatRequest.getPenerbitId()));
            }

            return cb.and(p.toArray(new Predicate[]{}));

        };
    }

    @Override
    public BaseResponse<?> saveBuku(BukuSimpanRequest simpanRequest) {
        Buku bukuSave = new Buku();
        String message = "";
        Optional<Buku> optBuku = bukuRepository.findById(simpanRequest.getBukuId());
        if (optBuku.isPresent()) {
            Buku buku = optBuku.get();
            buku.setJudulBuku(simpanRequest.getJudulBuku());
            buku.setTahunTerbit(simpanRequest.getTahunTerbit());
            buku.setJumlah(Long.valueOf(simpanRequest.getJumlah()));
            buku.setIsbn(simpanRequest.getIsbn());
            buku.setPengarangId(Long.valueOf(simpanRequest.getPengarangId()));
            buku.setPenerbitId(Long.valueOf(simpanRequest.getPenerbitId()));

            bukuSave = bukuRepository.save(buku);
            message = "Buku berhasil diperbaharui";
        } else {
            Buku buku = getBuku(simpanRequest);

            bukuSave = bukuRepository.save(buku);
            message = "Buku berhasil ditambahkan";
        }
        return BaseResponse.ok(message, bukuSave);
    }

    private static Buku getBuku(BukuSimpanRequest simpanRequest) {
        Buku buku = Buku.builder()
                .judulBuku(simpanRequest.getJudulBuku())
                .tahunTerbit(simpanRequest.getTahunTerbit())
                .jumlah(Long.valueOf(simpanRequest.getJumlah()))
                .isbn(simpanRequest.getIsbn())
                .pengarangId(Long.valueOf(simpanRequest.getPengarangId()))
                .penerbitId(Long.valueOf(simpanRequest.getPenerbitId()))
                .build();
        return buku;
    }
}
