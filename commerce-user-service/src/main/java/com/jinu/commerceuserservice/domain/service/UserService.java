package com.jinu.commerceuserservice.domain.service;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceuserservice.domain.dto.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdatePasswordRequestDto;
import com.jinu.commerceuserservice.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseBodyDto> signUpUser(SignUpRequestDto requestDto);

    ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(String email);

    ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(String email, String code);

    ResponseEntity<ResponseBodyDto> updateByInfo(HttpServletRequest req, UpdateInfoRequestDto requestDto);

    ResponseEntity<ResponseBodyDto> updateByPassword(HttpServletRequest req, UpdatePasswordRequestDto requestDto);

    void checkDuplicateByEmail(String mail);

    String createVerifyCode();

    void validatePassword(String inputPassword, String setPassword);

    User getUser(String email);
}