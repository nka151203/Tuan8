package com.example.wso2springdemo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/userinfo")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("subject", jwt.getSubject());
        userInfo.put("claims", jwt.getClaims());
        return userInfo;
    }

    @GetMapping("/protected")
    public Map<String, String> getProtectedData() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Đây là dữ liệu được bảo vệ");
        data.put("timestamp", LocalDateTime.now().toString());
        return data;
    }
}