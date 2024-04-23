package com.jinu.commerce.domain.order.repository;

import com.jinu.commerce.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
