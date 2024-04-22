package com.jinu.commerce.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jinu.commerce.domain.user.dto.request.UpdateInfoRequestDto;
import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String address;


    @Builder
    public User(String email, String password, String name, String mobile, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public void updateByInfo(UpdateInfoRequestDto requestDto) {
        this.mobile = requestDto.getMobile();
        this.address = requestDto.getAddress();
    }

    public void updateByPassword(String password) {
        this.password = password;
    }
}