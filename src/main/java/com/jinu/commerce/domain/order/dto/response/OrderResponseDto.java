package com.jinu.commerce.domain.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Builder
    public OrderResponseDto(Long orderId, OrderStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static List<OrderResponseDto> crateOrdersIntoResponseDtos(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderResponseDto.builder()
                        .orderId(order.getId())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build())
                .toList();
    }
}