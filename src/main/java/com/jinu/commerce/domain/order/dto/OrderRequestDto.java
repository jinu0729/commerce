package com.jinu.commerce.domain.order.dto;

import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private Long productId;
    private Long qty;


    @Builder
    public OrderRequestDto(Long productId, Long qty) {
        this.productId = productId;
        this.qty = qty;
    }
}
