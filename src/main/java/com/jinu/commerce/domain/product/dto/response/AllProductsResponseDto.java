package com.jinu.commerce.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllProductsResponseDto {
    private Long productId;
    private String title;
    private Long price;


    @Builder
    public AllProductsResponseDto(Long productId, String title, Long price) {
        this.productId = productId;
        this.title = title;
        this.price = price;
    }
}