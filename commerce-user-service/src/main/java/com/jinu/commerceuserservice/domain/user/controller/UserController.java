package com.jinu.commerceuserservice.domain.user.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceuserservice.domain.user.dto.request.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.user.dto.request.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.domain.user.dto.request.UpdatePasswordRequestDto;
import com.jinu.commerceuserservice.domain.user.service.UserService;
import com.jinu.commerceuserservice.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseBodyDto> signUpUser(@RequestBody SignUpRequestDto requestDto) {
        return userService.signUpUser(requestDto);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(@RequestBody SignUpRequestDto requestDto) {
        return userService.sendVerifyEmailForJoin(requestDto.getEmail());
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(@RequestParam(value = "email") String email,
                                                                  @RequestParam(value = "code") String code) {
        return userService.checkVerifyCodeForJoin(email, code);
    }

    @PutMapping("/update-info")
    public ResponseEntity<ResponseBodyDto> updateByInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody UpdateInfoRequestDto requestDto) {
        return userService.updateByInfo(userDetails, requestDto);
    }

    @PutMapping("/update-password")
    public ResponseEntity<ResponseBodyDto> updateByPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @RequestBody UpdatePasswordRequestDto requestDto) {
        return userService.updateByPassword(userDetails, requestDto);
    }
}
