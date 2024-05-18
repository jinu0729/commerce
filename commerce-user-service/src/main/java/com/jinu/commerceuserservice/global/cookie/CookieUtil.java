package com.jinu.commerceuserservice.global.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@Slf4j(topic = "CookieUtil")
@Component
public class CookieUtil {
    // HttpServletRequest 에서 Cookie Value : accessToken 가져오기
    public String getAccessTokenFromCookie(HttpServletRequest req) {
        log.info("accessToken 가져오기");

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            Optional<Cookie> authorizationCookie = findAuthorizationCookie(cookies);

            if (authorizationCookie.isPresent()) {
                return decodeCookieValue(authorizationCookie.get().getValue());
            }
        }

        return null;
    }

    private Optional<Cookie> findAuthorizationCookie(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> "Authorization".equals(cookie.getName()))
                .findFirst();
    }

    private String decodeCookieValue(String cookieValue) {
        try {
            return URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}