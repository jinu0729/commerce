package com.jinu.commerceorderservice.domain.order.entity;

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
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @Builder
    public Order(Long userId, Status status) {
        this.userId = userId;
        this.status = status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}