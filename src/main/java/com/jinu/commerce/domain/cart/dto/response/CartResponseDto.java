package com.jinu.commerce.domain.cart.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private Long orderId;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Builder
    public CartResponseDto(Long orderId, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

/*    public static List<CartResponseDto> crateOrdersIntoResponseDtos(List<Cart> carts) {
        return carts.stream()
                .map(order -> CartResponseDto.builder()
                        .orderId(order.getCartId())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build())
                .toList();
    }*/
}