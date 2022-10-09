package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.PengembalianSimpanRequest;
import com.irsan.sinaukoding.service.PengembalianService;
import com.irsan.sinaukoding.util.ApiParamAuth;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Pengembalian")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class PengembalianController {

    @Autowired
    private PengembalianService pengembalianService;

    @ApiOperation("Input Pengembalian Buku")
    @ApiParamAuth
    @PostMapping("/kembali")
    public BaseResponse<?> kembaliBuku(@RequestBody PengembalianSimpanRequest simpanRequest,
                                       HttpServletRequest httpServletRequest) throws ChangeSetPersister.NotFoundException {
        return pengembalianService.kembaliBuku(simpanRequest, httpServletRequest);
    }

}
