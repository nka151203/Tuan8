package com.example.hello_springbooot.configs;

import com.example.hello_springbooot.utils.SecurityUtils;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.beans.ExceptionListener;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
     public SecurityFilterChain fillterChain (HttpSecurity http) throws Exception {
         http
                 .csrf(c->c.disable())
                 .authorizeHttpRequests(
                         authz -> authz
                                 .requestMatchers("/login").permitAll()
//                                 .anyRequest().authenticated()
                                 .anyRequest().permitAll()
                 )
                 .formLogin(f -> f.disable())
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         return http.build();
     }
    @Bean
    public PasswordEncoder passowrdEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    }
    @Value("${an.jwt.base64-secret}")
    private String jwtKey;

    @Value("${an.jwt.token-validity-in-seconds}")
    private String jwtExpiration;
    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.from(jwtKey).decode(); //lấy jwtKey, giải mã từ Base64.
        //tạo và trả về SecretKey đã được mã hóa với thuật toán đã được định sẵn
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, SecurityUtils.JWT_ALGORITHM.getName());
    }
}
