package com.jinu.commerceauthservice.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String address;
}