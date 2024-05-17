package com.jinu.commerceauthservice.domain.entity;

import com.jinu.commerceauthservice.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Auth extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String refreshToken;


    @Builder
    public Auth(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
