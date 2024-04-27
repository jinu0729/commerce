package com.jinu.commerce.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequestDto {
    private String ogPassword;
    private String newPassword;
}