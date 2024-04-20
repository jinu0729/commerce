package com.jinu.commerce.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String mail;
    private String password;
    private String name;
    private String mobile;
    private String address;
}
