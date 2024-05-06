package com.jinu.commerceauthservice.global.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j(topic = "CookieUtil")
@Component
public class CookieUtil {
    // JWT Cookie 에 저장
    public void addJwtToCookie(String name, String token, HttpServletResponse res) {
        log.info("쿠키 저장");
        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행

            Cookie cookie = new Cookie(name, token); // Name-Value
            cookie.setPath("/");

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
    }

    public void deleteJwtFromCookie(String name, HttpServletResponse res) {
        log.info("쿠키 삭제");

        Cookie cookie = new Cookie(name, null);  // 쿠키 값을 null로 설정
        cookie.setMaxAge(0);  // 남은 만료시간을 0으로 설정

        res.addCookie(cookie);
    }

    // HttpServletRequest 에서 Cookie Value : accessToken 가져오기
    public String getAccessTokenFromRequestCookie(HttpServletRequest req) {
        log.info("accessToken 가져오기");

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8"); // Encode 되어 넘어간 Value 다시 Decode
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }

        return null;
    }
}