package com.jinu.commerce.domain.order.dto.response;

import com.jinu.commerce.domain.product.dto.response.ProductResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long orderDetailId;

    private ProductResponseDto product;

    private Long qty;


    @Builder
    public OrderDetailResponseDto(Long orderDetailId, ProductResponseDto product, Long qty) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.qty = qty;
    }
}