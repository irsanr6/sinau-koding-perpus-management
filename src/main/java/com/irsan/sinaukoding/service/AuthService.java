package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.model.LoginRequest;
import com.irsan.sinaukoding.model.RegisterRequest;
import com.irsan.sinaukoding.util.BaseResponse;

import java.io.IOException;

public interface AuthService {
    BaseResponse<?> authenticateUser(LoginRequest loginRequest);

    BaseResponse<?> registerUser(RegisterRequest registerRequest) throws IOException;
}
