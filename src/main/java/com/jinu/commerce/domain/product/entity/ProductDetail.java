package com.jinu.commerce.domain.product.entity;

import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductDetail extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;


    @Builder
    public ProductDetail(Long productId, String title, Long price, Long stock) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}
