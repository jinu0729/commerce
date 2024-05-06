package com.jinu.commerceauthservice.domain.service;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void saveRefreshToken(String email, String refreshToken);

    void signOut(String email, HttpServletResponse res);
}
