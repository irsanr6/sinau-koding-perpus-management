package com.irsan.sinaukoding.controller;

import com.irsan.sinaukoding.util.SessionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> testMasuk(HttpServletRequest request) {
        return ResponseEntity.ok(SessionUtil.getUserData(request));
    }

}
