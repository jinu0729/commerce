package com.jinu.commerceorderservice.domain.order.service;

import com.jinu.commerceorderservice.domain.order.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId);

    List<Order> getAllOrders(Long userId);

    Order getOrderByOrderId(Long orderId);

    void changeStatusToCancel(Long orderId);

    void changeStatusToReturn(Long orderId);

    void updateOrderStatus();
}
