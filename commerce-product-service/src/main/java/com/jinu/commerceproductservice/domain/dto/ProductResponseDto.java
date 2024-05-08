package com.jinu.commerceproductservice.domain.dto;

import com.jinu.commerceproductservice.domain.entity.Product;
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

    public static ProductResponseDto createProductResponseDtoIntoProduct(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}