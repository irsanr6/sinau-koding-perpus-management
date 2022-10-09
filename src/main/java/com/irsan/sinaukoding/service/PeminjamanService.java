package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.PeminjamanSimpanRequest;
import com.irsan.sinaukoding.util.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public interface PeminjamanService {
    BaseResponse<?> pinjamBuku(PeminjamanSimpanRequest pinjamRequest, HttpServletRequest httpServletRequest);

    BaseResponse<?> updatePinjamBuku(PeminjamanSimpanRequest pinjamRequest);
}
