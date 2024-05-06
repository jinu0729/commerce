package com.jinu.commerceauthservice.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestDto {
    private Long userId;
    private String refreshToken;


    @Builder
    public RefreshTokenRequestDto(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}