package com.jinu.commerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // user
    DUPLICATE_MAIL(HttpStatus.CONFLICT.value(), "DUPLICATE_MAIL", "mail 중복"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_USER", "가입되지 않은 사용자")

    // mail

    ;


    private final Integer status;
    private final String code;
    private final String message;
}
