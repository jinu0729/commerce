package com.jinu.commerce.domain.product.entity;

import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @OneToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;


    @Builder
    public Product(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}