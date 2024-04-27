package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.user.dto.request.SignUpRequestDto;
import com.jinu.commerce.domain.user.dto.request.UpdateInfoRequestDto;
import com.jinu.commerce.domain.user.dto.request.UpdatePasswordRequestDto;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseBodyDto> signUpUser(SignUpRequestDto requestDto);

    ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(String email);

    ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(String email, String code);

    ResponseEntity<ResponseBodyDto> updateByInfo(UserDetailsImpl userDetails, UpdateInfoRequestDto requestDto);

    ResponseEntity<ResponseBodyDto> updateByPassword(UserDetailsImpl userDetails, UpdatePasswordRequestDto requestDto);

    void checkDuplicateByEmail(String mail);

    String createVerifyCode();

    void validatePassword(String inputPassword, String setPassword);
}