package com.example.hello_springbooot.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class SecurityUtils {
    private final JwtEncoder jwtEncoder;
    public SecurityUtils(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512; //Định nghĩa thuật toán muốn dùng
    @Value("${an.jwt.token-validity-in-seconds}")
    private Long jwtExpiration;

    public String createToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.jwtExpiration, ChronoUnit.SECONDS);
        //Đây là phần Playload
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim("an", authentication)
                .build();
        //Đây là phần Header
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
