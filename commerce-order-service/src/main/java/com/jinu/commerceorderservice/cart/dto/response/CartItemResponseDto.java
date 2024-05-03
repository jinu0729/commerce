package com.jinu.commerceorderservice.cart.dto.response;

import com.jinu.commerceorderservice.cart.entity.CartItem;
import com.jinu.commerceproductservice.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CartItemResponseDto {
    private Long cartItemId;

    private Product product;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Builder
    public CartItemResponseDto(Long cartItemId, Product product, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static List<CartItemResponseDto> crateCartItemsIntoResponseDtos(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> builder()
                        .cartItemId(cartItem.getCartItemId())
                        .product(cartItem.getProduct())
                        .createdAt(cartItem.getCreatedAt())
                        .updatedAt(cartItem.getUpdatedAt())
                        .build())
                .toList();
    }
}