package com.jinu.commerceauthservice.domain.service;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void saveRefreshToken(Long userId, String refreshToken);

    void signOut(Long userId, HttpServletResponse res);
}
