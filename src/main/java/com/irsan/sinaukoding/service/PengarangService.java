package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.PengarangLihatRequest;
import com.irsan.sinaukoding.model.PengarangSimpanRequest;
import com.irsan.sinaukoding.util.BaseResponse;

public interface PengarangService {
    BaseResponse<?> getPengarangByField(PengarangLihatRequest lihatRequest);

    BaseResponse<?> savePengarang(PengarangSimpanRequest simpanRequest);
}
