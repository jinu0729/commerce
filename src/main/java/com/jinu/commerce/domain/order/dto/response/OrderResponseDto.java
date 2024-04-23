package com.jinu.commerce.domain.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;

    private Long price;

    private OrderStatus status;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<OrderDetailResponseDto> orderDetails;


    @Builder
    public OrderResponseDto(Long orderId, Long price, OrderStatus status, List<OrderDetailResponseDto> orderDetails) {
        this.orderId = orderId;
        this.price = price;
        this.status = status;
        this.orderDetails = orderDetails;
    }
}