package com.jinu.commerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // user
    DUPLICATE_EMAIL(HttpStatus.CONFLICT.value(), "DUPLICATE_EMAIL", "이메일 중복"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_USER", "가입되지 않은 사용자"),
    NOT_MATCHED_PASSWORD(HttpStatus.BAD_REQUEST.value(), "NOT_MATCHED_PASSWORD", "비밀번호 미일치"),

    // mail
    NOT_FOUND_CODE(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_VALUE", "인증코드 없음"),
    NOT_MATCHED_CODE(HttpStatus.BAD_REQUEST.value(), "NOT_MATCHED_CODE", "인증코드 미일치"),
    WRONG_EMAIL(HttpStatus.BAD_REQUEST.value(), "WRONG_EMAIL", "잘못된 이메일"),

    // product
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_PRODUCT", "해당상품 없음"),

    // token
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "EXPIRED_TOKEN", "만료된 토큰")
    ;


    private final Integer status;
    private final String code;
    private final String message;
}
