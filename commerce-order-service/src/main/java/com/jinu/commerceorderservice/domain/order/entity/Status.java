package com.jinu.commerceorderservice.domain.order.entity;

import lombok.Getter;

@Getter
public enum Status {
    PAYING("결제중"),
    PAID("결제완료"),
    IN_DELIVERY("배송중"),
    DELIVERED("배송완료"),
    CANCELED("취소"),
    RETURNED("반품");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}