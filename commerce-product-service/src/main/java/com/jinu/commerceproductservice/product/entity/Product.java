package com.jinu.commerceproductservice.product.entity;

import com.jinu.commerceproductservice.product.util.Timestamped;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;


    @Builder
    public Product(String title, Long price, Long stock) {
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}