package com.jinu.commerceorderservice.cart.entity;

import com.jinu.commercecommon.util.Timestamped;
import com.jinu.commerceproductservice.product.entity.Product;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long qty;


    @Builder
    public CartItem(Cart cart, Product product, Long qty) {
        this.cart = cart;
        this.product = product;
        this.qty = qty;
    }

    public void editQty(Long qty) {
        this.qty = qty;
    }
}