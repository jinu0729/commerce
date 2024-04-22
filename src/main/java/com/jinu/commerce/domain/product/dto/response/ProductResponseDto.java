package com.jinu.commerce.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private String title;
    private Long price;
    private Long stock;


    @Builder
    public ProductResponseDto(String title, Long price, Long stock) {
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}