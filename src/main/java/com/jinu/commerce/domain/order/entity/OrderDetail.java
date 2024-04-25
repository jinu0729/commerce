package com.jinu.commerce.domain.order.entity;

import com.jinu.commerce.domain.product.entity.Product;
import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_DETAIL")
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long qty;


    @Builder
    public OrderDetail(Product product, Order order, Long qty) {
        this.product = product;
        this.order = order;
        this.qty = qty;
    }
}