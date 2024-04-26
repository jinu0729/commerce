package com.jinu.commerce.domain.cart.dto.response;

import com.jinu.commerce.domain.cart.entity.CartItem;
import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CartDetailResponseDto {
    private Long orderDetailId;

    private Product product;

    private Long qty;


    @Builder
    public CartDetailResponseDto(Long orderDetailId, Product product, Long qty) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.qty = qty;
    }

    public static List<CartDetailResponseDto> createOrderDetailsIntoOrderDetailResponseDtos(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(orderDetail -> CartDetailResponseDto.builder()
                        .orderDetailId(orderDetail.getCartItemId())
                        .product(orderDetail.getProduct())
                        .qty(orderDetail.getQty())
                        .build())
                .toList();
    }
}