package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.PeminjamanSimpanRequest;
import com.irsan.sinaukoding.service.PeminjamanService;
import com.irsan.sinaukoding.util.ApiParamAuth;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Peminjaman")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    @ApiOperation("Input Peminjaman Buku")
    @ApiParamAuth
    @PostMapping("/pinjam")
    public BaseResponse<?> pinjamBuku(@RequestBody PeminjamanSimpanRequest pinjamRequest,
                                      HttpServletRequest httpServletRequest) {
        return peminjamanService.pinjamBuku(pinjamRequest, httpServletRequest);
    }

    @ApiOperation("Update Peminjaman Buku")
    @ApiParamAuth
    @PostMapping("/updatePinjam")
    public BaseResponse<?> updatePinjamBuku(@RequestBody PeminjamanSimpanRequest pinjamRequest) {
        return peminjamanService.updatePinjamBuku(pinjamRequest);
    }

}
