package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.BukuLihatRequest;
import com.irsan.sinaukoding.model.BukuSimpanRequest;
import com.irsan.sinaukoding.util.BaseResponse;

public interface BukuService {
    BaseResponse<?> getAllBuku(BukuLihatRequest lihatRequest);

    BaseResponse<?> saveBuku(BukuSimpanRequest simpanRequest);
}
