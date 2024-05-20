package com.jinu.commerceuserservice.domain.service;

import com.jinu.commerceuserservice.domain.dto.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdatePasswordRequestDto;
import com.jinu.commerceuserservice.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    void signUpUser(SignUpRequestDto requestDto);

    void sendVerifyEmailForJoin(String email);

    void checkVerifyCodeForJoin(String email, String code);

    void updateByInfo(HttpServletRequest req, UpdateInfoRequestDto requestDto);

    void updateByPassword(HttpServletRequest req, UpdatePasswordRequestDto requestDto);

    User getUserByEmail(String email);
}