package com.jinu.commerceproductservice.domain.entity;

import lombok.Getter;

@Getter
public enum Type {
    NORMAL("일반상품"),
    LIMITED("한정");

    private final String status;

    Type(String status) {
        this.status = status;
    }
}
