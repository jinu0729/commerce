package com.jinu.commerceorderservice.domain.order.dto.request;

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
