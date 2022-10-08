package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.BukuLihatRequest;
import com.irsan.sinaukoding.model.BukuSimpanRequest;
import com.irsan.sinaukoding.service.BukuService;
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

@Api(tags = "Buku")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class BukuController {

    @Autowired
    private BukuService bukuService;

    @ApiOperation("Lihat Buku")
    @ApiParamAuth
    @PostMapping("/getAllBuku")
    public BaseResponse<?> getAllBuku(@RequestBody BukuLihatRequest lihatRequest) {
        return bukuService.getAllBuku(lihatRequest);
    }

    @ApiOperation("Simpan Buku")
    @ApiParamAuth
    @PostMapping("/saveBuku")
    public BaseResponse<?> saveBuku(@RequestBody BukuSimpanRequest simpanRequest) {
        return bukuService.saveBuku(simpanRequest);
    }

}
