package com.jinu.commerceuserservice.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequestDto {
    private String ogPassword;
    private String newPassword;
}