package com.jinu.commerceuserservice.user.service;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceuserservice.security.UserDetailsImpl;
import com.jinu.commerceuserservice.user.dto.request.SignUpRequestDto;
import com.jinu.commerceuserservice.user.dto.request.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.user.dto.request.UpdatePasswordRequestDto;
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