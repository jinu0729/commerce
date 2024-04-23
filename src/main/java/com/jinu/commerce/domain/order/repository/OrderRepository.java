package com.jinu.commerce.domain.order.repository;

import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
