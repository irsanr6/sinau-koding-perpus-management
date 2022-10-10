package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.util.ApiParamAuth;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Test")
@RestController
@RequestMapping("/api/v1")
public class TestController {

    @ApiOperation("Hanya Test Controller")
    @ApiParamAuth
    @GetMapping("/test")
    public BaseResponse<?> testMasuk(HttpServletRequest request) {
        return BaseResponse.ok(SessionUtil.getUserData(request));
    }

}
