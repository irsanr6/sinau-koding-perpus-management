package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Pengarang;
import com.irsan.sinaukoding.model.PengarangLihatRequest;
import com.irsan.sinaukoding.model.PengarangSimpanRequest;
import com.irsan.sinaukoding.repository.PengarangRepository;
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
public class PengarangServiceImpl implements PengarangService{

    @Autowired
    private PengarangRepository pengarangRepository;

    @Override
    public BaseResponse<?> getPengarangByField(PengarangLihatRequest lihatRequest) {
        Pageable pageable = Helper.getPageRequest(lihatRequest.getPageIn(), lihatRequest.getLimit(), "namaPengarang");
        Page<Pengarang> pengarangPage = pengarangRepository.findAll(findSpec(lihatRequest), pageable);
        return BaseResponse.okPage(pengarangPage.getContent(), pengarangPage);
    }

    private Specification<Pengarang> findSpec(PengarangLihatRequest lihatRequest) {
        return (Root<Pengarang> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> p = new ArrayList<>();

            if (StringUtils.isNotBlank(lihatRequest.getPengarangId())) {
                p.add(cb.equal(root.get("pengarangId"), lihatRequest.getPengarangId()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getNamaPengarang())) {
                p.add(cb.like(root.get("namaPengarang"), "%" + lihatRequest.getNamaPengarang() + "%"));
            }
            if (StringUtils.isNotBlank(lihatRequest.getAlamat())) {
                p.add(cb.like(root.get("alamat"), "%" + lihatRequest.getAlamat() + "%"));
            }

            return cb.and(p.toArray(new Predicate[]{}));

        };
    }

    @Override
    public BaseResponse<?> savePengarang(PengarangSimpanRequest simpanRequest) {
        Pengarang pengarangSave = new Pengarang();
        String message = "";
        Optional<Pengarang> optPengarang = pengarangRepository.findById(simpanRequest.getPengarangId());
        if (optPengarang.isPresent()) {
            Pengarang pengarang = optPengarang.get();
            pengarang.setNamaPengarang(simpanRequest.getNamaPengarang());
            pengarang.setAlamat(simpanRequest.getAlamat());
            pengarang.setTelp(simpanRequest.getTelp());

            pengarangSave = pengarangRepository.save(pengarang);
            message = "Pengarang berhasil diperbaharui";
        } else {
            Pengarang pengarang = getPengarang(simpanRequest);

            pengarangSave = pengarangRepository.save(pengarang);
            message = "Pengarang berhasil ditambah";
        }
        return BaseResponse.ok(message, pengarangSave);
    }

    private Pengarang getPengarang(PengarangSimpanRequest simpanRequest) {
        return Pengarang.builder()
                .namaPengarang(simpanRequest.getNamaPengarang())
                .alamat(simpanRequest.getAlamat())
                .telp(simpanRequest.getTelp())
                .build();
    }
}
