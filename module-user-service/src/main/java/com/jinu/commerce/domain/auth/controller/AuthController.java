package com.jinu.commerce.domain.auth.controller;

import com.jinu.commerce.domain.auth.service.AuthService;
import com.jinu.commerce.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        this.authService.signOut(userDetails.getUsername(), res);
    }
}
