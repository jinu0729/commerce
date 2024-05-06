package com.jinu.commerceuserservice.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String address;
}
