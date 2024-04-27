package com.jinu.commerce.domain.order.dto.request;

import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private Product product;
    private Long qty;


    @Builder
    public OrderRequestDto(Product product, Long qty) {
        this.product = product;
        this.qty = qty;
    }
}
