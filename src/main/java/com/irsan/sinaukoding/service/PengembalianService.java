package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.PengembalianSimpanRequest;
import com.irsan.sinaukoding.util.BaseResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.servlet.http.HttpServletRequest;

public interface PengembalianService {
    BaseResponse<?> kembaliBuku(PengembalianSimpanRequest simpanRequest, HttpServletRequest httpServletRequest) throws ChangeSetPersister.NotFoundException;
}
