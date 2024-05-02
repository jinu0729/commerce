package com.jinu.commerceuserservice.user.dto.request;

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
