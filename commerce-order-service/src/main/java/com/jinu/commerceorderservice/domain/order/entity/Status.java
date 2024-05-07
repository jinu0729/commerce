package com.jinu.commerceorderservice.domain.order.entity;

import lombok.Getter;

@Getter
public enum Status {
    ORDER_COMPLETE("주문완료"),
    IN_DELIVERY("배송중"),
    DELIVERED("배송완료"),
    CANCEL("주문취소"),
    RETURN("반품");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
