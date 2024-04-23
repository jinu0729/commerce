package com.jinu.commerce.domain.product.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;

    private String title;

    private Long price;

    private Long stock;


    @Builder
    public ProductResponseDto(Long productId, String title, Long price, Long stock) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}