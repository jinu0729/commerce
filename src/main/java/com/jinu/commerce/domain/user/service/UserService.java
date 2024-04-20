package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseBodyDto> signUpUser(UserRequestDto requestDto);

    ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(UserRequestDto requestDto);

    void checkDuplicateByEmail(String mail);

    int createVerifyCode();
}