package com.jinu.commerceauthservice.domain.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String email;
    private String password;
}
