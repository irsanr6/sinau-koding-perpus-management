package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.PenerbitLihatRequest;
import com.irsan.sinaukoding.model.PenerbitSimpanRequest;
import com.irsan.sinaukoding.util.BaseResponse;

public interface PenerbitService {
    BaseResponse<?> getPenerbitByField(PenerbitLihatRequest lihatRequest);

    BaseResponse<?> savePenerbit(PenerbitSimpanRequest simpanRequest);
}
