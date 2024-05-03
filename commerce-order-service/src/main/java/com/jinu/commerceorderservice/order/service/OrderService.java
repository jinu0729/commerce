package com.jinu.commerceorderservice.order.service;

import com.jinu.commerceorderservice.order.entity.Order;
import com.jinu.commerceuserservice.security.UserDetailsImpl;

import java.util.List;

public interface OrderService {
    Order createOrder(UserDetailsImpl userDetails);

    List<Order> getAllOrders(UserDetailsImpl userDetails);

    Order getOrderByOrderId(Long orderId);

    void changeStatusToCancel(Long orderId);

    void changeStatusToReturn(Long orderId);

    void updateOrderStatus();
}
