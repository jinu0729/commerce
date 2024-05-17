package com.jinu.commerceauthservice.domain.service;

import com.jinu.commerceauthservice.domain.entity.Auth;
import com.jinu.commerceauthservice.domain.repository.AuthRepository;
import com.jinu.commerceauthservice.global.cookie.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "AuthServiceImpl")
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieUtil cookieUtil;

    @Override
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {
        log.info("refreshToken 저장");

        Auth auth = Auth.builder()
                .userId(userId)
                .refreshToken(passwordEncoder.encode(refreshToken))
                .build();

        this.authRepository.save(auth);
    }

    @Override
    @Transactional
    public void signOut(Long userId, HttpServletResponse res) {
        log.info("로그아웃");

        this.authRepository.deleteByUserId(userId);

        this.cookieUtil.deleteJwtFromCookie("Authorization", res);
        this.cookieUtil.deleteJwtFromCookie("Refresh-Token", res);
    }
}