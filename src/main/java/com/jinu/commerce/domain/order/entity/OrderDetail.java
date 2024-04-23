package com.jinu.commerce.domain.order.entity;

import com.jinu.commerce.domain.product.entity.Product;
import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderDetail extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long qty;


    @Builder
    public OrderDetail(Order order, Product product, Long qty) {
        this.order = order;
        this.product = product;
        this.qty = qty;
    }
}
