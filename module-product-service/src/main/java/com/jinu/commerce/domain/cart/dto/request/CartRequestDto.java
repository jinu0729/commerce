package com.jinu.commerce.domain.cart.dto.request;

import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {
    private Product product;
    private Long qty;


    @Builder
    public CartRequestDto(Product product, Long qty) {
        this.product = product;
        this.qty = qty;
    }
}