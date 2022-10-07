package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.UserPerpus;
import com.irsan.sinaukoding.model.*;
import com.irsan.sinaukoding.repository.UserRepository;
import com.irsan.sinaukoding.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<?> authenticateUser(LoginRequest loginRequest) {
        getStringBaseResponse(loginRequest);
        final UserPerpus userPerpus =userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail()).get();
        UserData userData = UserData.builder()
                .userId(userPerpus.getUserId())
                .nama("Unknown")
                .username(userPerpus.getUsername())
                .email(userPerpus.getEmail())
                .alamat("-")
                .telp("-")
                .role(userPerpus.getRole())
                .build();
        final String token = jwtTokenUtil.generateToken(userData);
        return BaseResponse.ok(UserAccessResponse.builder()
                .userAccess(UserAccessResponse.UserAccess.builder()
                        .fullName(userData.getNama())
                        .username(userData.getUsername())
                        .email(userData.getEmail())
                        .role(userPerpus.getRole())
                        .build())
                .token(token)
                .build());
    }

    private void getStringBaseResponse(LoginRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsernameOrEmail(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            BaseResponse.ok("User signed-in successfully!.");
        } catch (BadCredentialsException exception) {
            BaseResponse.error("User signed-in failed!.", exception.getMessage());
        }
    }

    @Override
    public BaseResponse<?> registerUser(RegisterRequest registerRequest) throws IOException {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return BaseResponse.error200("Username is already taken!");
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return BaseResponse.error200("Email is already taken!");
        }

        UserPerpus userPerpus = UserPerpus.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .passwordEncode(CompressionUtil.compressB64(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .createdAt(Helper.currentDate())
                .updatedAt(Helper.currentDate())
                .role(Constant.ROLE_DEFAULT)
                .build();

        UserPerpus save = userRepository.save(userPerpus);

        RegisterResponse registerResponse = RegisterResponse.builder()
                .userId(save.getUserId())
                .username(save.getUsername())
                .email(save.getEmail())
                .build();

        return BaseResponse.ok("User registered successfully", registerResponse);
    }
}
