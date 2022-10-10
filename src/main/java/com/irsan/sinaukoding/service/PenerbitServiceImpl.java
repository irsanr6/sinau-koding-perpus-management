package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.Buku;
import com.irsan.sinaukoding.entity.Penerbit;
import com.irsan.sinaukoding.model.BukuLihatRequest;
import com.irsan.sinaukoding.model.PenerbitLihatRequest;
import com.irsan.sinaukoding.model.PenerbitSimpanRequest;
import com.irsan.sinaukoding.repository.PenerbitRepository;
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
public class PenerbitServiceImpl implements PenerbitService {

    @Autowired
    private PenerbitRepository penerbitRepository;

    @Override
    public BaseResponse<?> getPenerbitByField(PenerbitLihatRequest lihatRequest) {
        Pageable pageable = Helper.getPageRequest(lihatRequest.getPageIn(), lihatRequest.getLimit(), "namaPenerbit");
        Page<Penerbit> penerbitPage = penerbitRepository.findAll(findSpec(lihatRequest), pageable);
        return BaseResponse.okPage(penerbitPage.getContent(), penerbitPage);
    }

    private Specification<Penerbit> findSpec(PenerbitLihatRequest lihatRequest) {
        return (Root<Penerbit> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> p = new ArrayList<>();

            if (StringUtils.isNotBlank(lihatRequest.getPenerbitId())) {
                p.add(cb.equal(root.get("penerbitId"), lihatRequest.getPenerbitId()));
            }
            if (StringUtils.isNotBlank(lihatRequest.getNamaPenerbit())) {
                p.add(cb.like(root.get("namaPenerbit"), "%" + lihatRequest.getNamaPenerbit() + "%"));
            }
            if (StringUtils.isNotBlank(lihatRequest.getAlamat())) {
                p.add(cb.like(root.get("alamat"), "%" + lihatRequest.getAlamat() + "%"));
            }

            return cb.and(p.toArray(new Predicate[]{}));

        };
    }

    @Override
    public BaseResponse<?> savePenerbit(PenerbitSimpanRequest simpanRequest) {
        Penerbit penerbitSave = new Penerbit();
        String message = "";
        Optional<Penerbit> optPenerbit = penerbitRepository.findById(simpanRequest.getPenerbitId());
        if (optPenerbit.isPresent()) {
            Penerbit penerbit = optPenerbit.get();
            penerbit.setNamaPenerbit(simpanRequest.getNamaPenerbit());
            penerbit.setAlamat(simpanRequest.getAlamat());
            penerbit.setTelp(simpanRequest.getTelp());

            penerbitSave = penerbitRepository.save(penerbit);
            message = "Penerbit berhasil diperbaharui";
        } else {
            Penerbit penerbit = getPenerbit(simpanRequest);

            penerbitSave = penerbitRepository.save(penerbit);
            message = "Penerbit berhasil ditambahkan";
        }
        return BaseResponse.ok(message, penerbitSave);
    }

    private Penerbit getPenerbit(PenerbitSimpanRequest simpanRequest) {
        return Penerbit.builder()
                .namaPenerbit(simpanRequest.getNamaPenerbit())
                .alamat(simpanRequest.getAlamat())
                .telp(simpanRequest.getTelp())
                .build();
    }
}
