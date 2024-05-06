package com.jinu.commerceuserservice.domain.auth.controller;

import com.jinu.commerceuserservice.domain.auth.service.AuthService;
import com.jinu.commerceuserservice.global.security.UserDetailsImpl;
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
