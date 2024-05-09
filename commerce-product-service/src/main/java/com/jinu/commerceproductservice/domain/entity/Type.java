package com.jinu.commerceproductservice.domain.entity;

import lombok.Getter;

@Getter
public enum Type {
    NORMAL("일반구매"),
    PREORDER("예약구매");

    private final String status;

    Type(String status) {
        this.status = status;
    }
}
