package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.PengarangLihatRequest;
import com.irsan.sinaukoding.model.PengarangSimpanRequest;
import com.irsan.sinaukoding.service.PengarangService;
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

@Api(tags = "Pengarang")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class PengarangController {

    @Autowired
    private PengarangService pengarangService;

    @ApiOperation("Lihat Pengarang")
    @ApiParamAuth
    @PostMapping("/getPengarang")
    public BaseResponse<?> getPengarang(@RequestBody PengarangLihatRequest lihatRequest) {
        return pengarangService.getPengarangByField(lihatRequest);
    }

    @ApiOperation("Simpan Pengarang")
    @ApiParamAuth
    @PostMapping("/savePengarang")
    public BaseResponse<?> savePengarang(@RequestBody PengarangSimpanRequest simpanRequest) {
        return pengarangService.savePengarang(simpanRequest);
    }

}
