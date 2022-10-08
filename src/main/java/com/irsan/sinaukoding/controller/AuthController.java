package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.model.LoginRequest;
import com.irsan.sinaukoding.model.RegisterRequest;
import com.irsan.sinaukoding.service.AuthService;
import com.irsan.sinaukoding.util.BaseResponse;
import com.irsan.sinaukoding.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(tags = "Auth")
@RestController
@RequestMapping(Constant.BASE_PATH)
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("Login User")
    @PostMapping("/signin")
    public BaseResponse<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws BadCredentialsException {
        return authService.authenticateUser(loginRequest);
    }

    @ApiOperation("Register User")
    @PostMapping("/signup")
    public BaseResponse<?> registerUser(@RequestBody RegisterRequest registerRequest) throws IOException {
        return authService.registerUser(registerRequest);
    }

}
