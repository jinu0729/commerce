package com.jinu.commerceuserservice.domain.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceuserservice.domain.dto.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.entity.User;
import com.jinu.commerceuserservice.domain.service.UserService;
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
        return userService.signUpUser(requestDto);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(@RequestBody SignUpRequestDto requestDto) {
        return userService.sendVerifyEmailForJoin(requestDto.getEmail());
    }

    @GetMapping()
    public User getUser(@RequestParam(value = "email") String email) {
        return userService.getUser(email);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(@RequestParam(value = "email") String email,
                                                                  @RequestParam(value = "code") String code) {
        return userService.checkVerifyCodeForJoin(email, code);
    }

 /*   @PutMapping("/update-info")
    public ResponseEntity<ResponseBodyDto> updateByInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody UpdateInfoRequestDto requestDto) {
        return userService.updateByInfo(userDetails, requestDto);
    }

    @PutMapping("/update-password")
    public ResponseEntity<ResponseBodyDto> updateByPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @RequestBody UpdatePasswordRequestDto requestDto) {
        return userService.updateByPassword(userDetails, requestDto);
    }*/
}
