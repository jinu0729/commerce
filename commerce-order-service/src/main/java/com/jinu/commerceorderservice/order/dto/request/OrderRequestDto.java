package com.jinu.commerceorderservice.order.dto.request;

import com.jinu.commerceproductservice.product.entity.Product;
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
