package com.jinu.commerceorderservice.cart.dto.request;

import com.jinu.commerceproductservice.product.entity.Product;
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