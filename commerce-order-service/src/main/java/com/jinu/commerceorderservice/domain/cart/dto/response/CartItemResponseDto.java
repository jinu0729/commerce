package com.jinu.commerceorderservice.domain.cart.dto.response;

import com.jinu.commerceorderservice.domain.cart.entity.CartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CartItemResponseDto {
    private Long cartItemId;
    private Long productId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public CartItemResponseDto(Long cartItemId, Long productId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static List<CartItemResponseDto> crateCartItemsIntoResponseDtoList(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> builder()
                        .cartItemId(cartItem.getCartItemId())
                        .productId(cartItem.getProductId())
                        .createdAt(cartItem.getCreatedAt())
                        .updatedAt(cartItem.getUpdatedAt())
                        .build())
                .toList();
    }
}