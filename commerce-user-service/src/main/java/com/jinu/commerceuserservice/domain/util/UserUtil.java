package com.jinu.commerceuserservice.domain.util;

import com.jinu.commerceuserservice.global.cookie.CookieUtil;
import com.jinu.commerceuserservice.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;

    public Long getUserIdFromToken(HttpServletRequest req) {
        String accessToken = this.cookieUtil.getAccessTokenFromCookie(req);
        String subStringToken = this.jwtUtil.substringToken(accessToken);

        return this.jwtUtil.getUserIdFromToken(subStringToken);
    }
}