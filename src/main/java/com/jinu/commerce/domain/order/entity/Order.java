package com.jinu.commerce.domain.order.entity;

import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER")
@Setter
@Getter
@NoArgsConstructor
public class Order  extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private OrderStatus status;


    @Builder
    public Order(User user, OrderStatus status) {
        this.user = user;
        this.status = status;
    }
}