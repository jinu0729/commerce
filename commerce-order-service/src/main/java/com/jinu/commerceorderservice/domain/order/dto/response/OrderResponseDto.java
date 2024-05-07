package com.jinu.commerceorderservice.domain.order.dto.response;

import com.jinu.commerceorderservice.domain.order.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Builder
    public OrderResponseDto(Long orderId, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static List<OrderResponseDto> crateOrdersIntoResponseDtos(List<Order> orders) {
        return orders.stream()
                .map(order -> builder()
                        .orderId(order.getOrderId())
                        .status(order.getStatus().getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build())
                .toList();
    }
}