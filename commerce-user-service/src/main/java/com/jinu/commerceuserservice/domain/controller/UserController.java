package com.jinu.commerceuserservice.domain.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceuserservice.domain.dto.EmailRequestDto;
import com.jinu.commerceuserservice.domain.dto.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdatePasswordRequestDto;
import com.jinu.commerceuserservice.domain.entity.User;
import com.jinu.commerceuserservice.domain.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseBodyDto> signUpUser(@RequestBody SignUpRequestDto requestDto) {
        this.userService.signUpUser(requestDto);

        return ResponseEntity.ok(ResponseBodyDto.success("가입완료"));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(@RequestBody EmailRequestDto requestDto) {
        this.userService.sendVerifyEmailForJoin(requestDto.getEmail());

        return ResponseEntity.ok(ResponseBodyDto.success("발송완료"));
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(@RequestParam(value = "email") String email,
                                                                  @RequestParam(value = "code") String code) {
        this.userService.checkVerifyCodeForJoin(email, code);

        return ResponseEntity.ok(ResponseBodyDto.success("인증완료"));
    }

    @PutMapping("/update-info")
    public ResponseEntity<ResponseBodyDto> updateByInfo(HttpServletRequest req,
                                                        @RequestBody UpdateInfoRequestDto requestDto) {
        this.userService.updateByInfo(req, requestDto);

        return ResponseEntity.ok(ResponseBodyDto.success("개인정보 업데이트 완료"));
    }

    @PutMapping("/update-password")
    public ResponseEntity<ResponseBodyDto> updateByPassword(HttpServletRequest req,
                                                            @RequestBody UpdatePasswordRequestDto requestDto) {
        this.userService.updateByPassword(req, requestDto);

        return ResponseEntity.ok(ResponseBodyDto.success("패스워드 업데이트 완료"));
    }

    @GetMapping("/internal")
    public User getUserByEmail(@RequestParam(value = "email") String email) {
        return this.userService.getUserByEmail(email);
    }
}