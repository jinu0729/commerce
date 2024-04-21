package com.jinu.commerce.domain.auth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String refreshToken;


    @Builder
    public Auth(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
