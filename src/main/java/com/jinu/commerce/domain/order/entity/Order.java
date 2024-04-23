package com.jinu.commerce.domain.order.entity;

import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "`order`")
@Setter
@Getter
@NoArgsConstructor
public class Order  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long price;

    @Column
    private Enum<OrderStatus> status;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderDetail> orderDetails;


    @Builder
    public Order(User user, Long price, Enum<OrderStatus> status, List<OrderDetail> orderDetails) {
        this.user = user;
        this.price = price;
        this.status = status;
        this.orderDetails = orderDetails;
    }
}
