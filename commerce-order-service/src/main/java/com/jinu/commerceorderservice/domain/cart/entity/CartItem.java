package com.jinu.commerceorderservice.domain.cart.entity;

import com.jinu.commerceorderservice.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long qty;


    @Builder
    public CartItem(Cart cart, Long productId, Long qty) {
        this.cart = cart;
        this.productId = productId;
        this.qty = qty;
    }

    public void editQty(Long qty) {
        this.qty = qty;
    }
}