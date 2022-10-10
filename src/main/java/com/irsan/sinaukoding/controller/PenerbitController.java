package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.PenerbitLihatRequest;
import com.irsan.sinaukoding.model.PenerbitSimpanRequest;
import com.irsan.sinaukoding.service.PenerbitService;
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

@Api(tags = "Penerbit")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class PenerbitController {

    @Autowired
    private PenerbitService penerbitService;

    @ApiOperation("Lihat Penerbit")
    @ApiParamAuth
    @PostMapping("/getPenerbit")
    public BaseResponse<?> getPenerbit(@RequestBody PenerbitLihatRequest lihatRequest) {
        return penerbitService.getPenerbitByField(lihatRequest);
    }

    @ApiOperation("Simpan Penerbit")
    @ApiParamAuth
    @PostMapping("/savePenerbit")
    public BaseResponse<?> savePenerbit(@RequestBody PenerbitSimpanRequest simpanRequest) {
        return penerbitService.savePenerbit(simpanRequest);
    }

}
