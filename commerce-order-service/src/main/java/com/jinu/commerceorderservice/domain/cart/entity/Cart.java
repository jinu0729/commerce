package com.jinu.commerceorderservice.domain.cart.entity;

import com.jinu.commerceorderservice.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cart extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(nullable = false)
    private Long userId;


    @Builder
    public Cart(Long userId) {
        this.userId = userId;
    }
}