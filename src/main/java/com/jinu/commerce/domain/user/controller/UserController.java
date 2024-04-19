package com.jinu.commerce.domain.user.controller;

import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.domain.user.service.UserService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseBodyDto> signUpUser(@RequestBody UserRequestDto signUpDto) {
        return service.signUpUser(signUpDto);
    }

    @GetMapping("/check-email")
    public ResponseEntity<ResponseBodyDto> checkDuplicateByEmail(@RequestParam(value = "email") String email) {
        return service.checkDuplicateByEmail(email);
    }
}
