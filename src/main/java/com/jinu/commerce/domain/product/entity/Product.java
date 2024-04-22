package com.jinu.commerce.domain.product.entity;

import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @JoinColumn(name = "productDetailId", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private ProductDetail productDetail;
}