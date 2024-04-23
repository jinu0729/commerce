package com.jinu.commerce.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailResponseDto {
    private Long productDetailId;
    private String title;
    private Long price;
    private Long stock;


    @Builder
    public ProductDetailResponseDto(Long productDetailId, String title, Long price, Long stock) {
        this.productDetailId = productDetailId;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}