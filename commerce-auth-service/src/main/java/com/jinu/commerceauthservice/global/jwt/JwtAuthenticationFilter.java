package com.jinu.commerceauthservice.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinu.commerceauthservice.domain.dto.SignInRequestDto;
import com.jinu.commerceauthservice.global.cookie.CookieUtil;
import com.jinu.commerceauthservice.global.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CookieUtil cookieUtil) {
        setFilterProcessesUrl("/api/auth/sign-in");
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        log.info("로그인 시도");

        try {
            SignInRequestDto requestDto = new ObjectMapper().readValue(req.getInputStream(), SignInRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");

        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        Long userId = ((UserDetailsImpl) authResult.getPrincipal()).getUserId();
        // UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        String accessToken = jwtUtil.createAccessToken(username, userId);
        String refreshToken = jwtUtil.createRefreshToken(username, userId);

        cookieUtil.addJwtToCookie("Authorization", accessToken, res);
        cookieUtil.addJwtToCookie("Refresh-Token", refreshToken, res);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");

        res.setStatus(401);
    }
}