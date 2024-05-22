package com.jinu.commerceproductservice.domain.entity;

import com.jinu.commerceproductservice.global.util.Timestamped;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Boolean isActive;


    @Builder
    public Product(Type type, String title, Long price, Long stock, Boolean isActive) {
        this.type = type;
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.isActive = isActive;
    }

    public void decreaseStock() {
        this.stock -= 1;
    }

    public void increaseStock() {
        this.stock += 1;
    }

    public void updateIsActiveStatus(Boolean isActive) {
        this.isActive = isActive;
    }
}