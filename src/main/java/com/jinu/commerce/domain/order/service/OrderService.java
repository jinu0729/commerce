package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.response.OrderResponseDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.global.security.UserDetailsImpl;

import java.util.List;

public interface OrderService {
    Order createOrder(UserDetailsImpl userDetails);

    List<OrderResponseDto> getAllOrders(UserDetailsImpl userDetails);

    /* ResponseEntity<ResponseBodyDto> getOrderDetailByOrderId(Long orderId);

    Order findById(Long orderId);*/
}
