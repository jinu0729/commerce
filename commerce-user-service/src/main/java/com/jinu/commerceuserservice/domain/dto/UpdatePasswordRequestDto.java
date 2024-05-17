package com.jinu.commerceuserservice.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequestDto {
    private String ogPassword;
    private String newPassword;
}