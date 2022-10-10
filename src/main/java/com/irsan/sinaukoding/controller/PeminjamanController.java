package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.PeminjamanSimpanRequest;
import com.irsan.sinaukoding.service.PeminjamanService;
import com.irsan.sinaukoding.util.ApiParamAuth;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("Track Peminjaman SELESAI")
    @ApiParamAuth
    @GetMapping("/trackPeminjamanSelesai")
    public BaseResponse<?> trackPeminjamanSelesai(@RequestParam(required = false) @ApiParam(required = false) Long anggotaId) {
        return peminjamanService.trackPeminjamanSelesai(anggotaId);
    }

    @ApiOperation("Track Peminjaman PINJAM")
    @ApiParamAuth
    @GetMapping("/trackPeminjamanPinjam")
    public BaseResponse<?> trackPeminjamanPinjam(@RequestParam(required = false) @ApiParam(required = false) Long anggotaId) {
        return peminjamanService.trackPeminjamanPinjam(anggotaId);
    }

    @ApiOperation("Track Peminjaman")
    @ApiParamAuth
    @GetMapping("/trackPeminjaman")
    public BaseResponse<?> trackPeminjaman() {
        return peminjamanService.trackPeminjaman();
    }

}
