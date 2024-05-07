package com.jinu.commerceorderservice.domain.cart.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {
    private Long productId;
    private Long qty;


    @Builder
    public CartRequestDto(Long productId, Long qty) {
        this.productId = productId;
        this.qty = qty;
    }
}