package com.jinu.commerce.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private ProductDetailResponseDto productDetails;


    @Builder
    public ProductResponseDto(Long productId, ProductDetailResponseDto productDetails) {
        this.productId = productId;
        this.productDetails = productDetails;
    }
}