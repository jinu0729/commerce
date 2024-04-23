package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<ResponseBodyDto> registerOrder(UserDetailsImpl userDetails, List<OrderRequestDto> requestDtos);

    ResponseEntity<ResponseBodyDto> getAllOrders(UserDetailsImpl userDetails);

    ResponseEntity<ResponseBodyDto> getOrderDetailByOrderId(Long orderId);

    Order findById(Long orderId);
}
