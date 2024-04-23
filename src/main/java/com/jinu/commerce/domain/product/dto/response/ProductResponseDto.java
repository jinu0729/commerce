package com.jinu.commerce.domain.product.dto.response;

import com.jinu.commerce.domain.product.entity.ProductDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private ProductDetailResponseDto detailResponseDto;


    @Builder
    public ProductResponseDto(Long productId, ProductDetailResponseDto detailResponseDto) {
        this.productId = productId;
        this.detailResponseDto = detailResponseDto;
    }
}