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
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseBodyDto> signUpUser(@RequestBody UserRequestDto requestDto) {
        return userService.signUpUser(requestDto);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(@RequestBody UserRequestDto requestDto) {
        return userService.sendVerifyEmailForJoin(requestDto.getEmail());
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(@RequestParam(value = "email") String email,
                                                                  @RequestParam(value = "code") String code) {
        return userService.checkVerifyCodeForJoin(email, code);
    }
}
