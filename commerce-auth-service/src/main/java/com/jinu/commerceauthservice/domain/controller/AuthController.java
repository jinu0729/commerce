package com.jinu.commerceauthservice.domain.controller;

import com.jinu.commerceauthservice.domain.service.AuthService;
import com.jinu.commerceauthservice.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @DeleteMapping("/sign-out")
    public void signOut(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse res) {
        this.authService.signOut(userDetails.getUserId(), res);
    }
}
