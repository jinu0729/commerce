package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.entity.Order;

import java.util.List;

public interface OrderDetailService {
    void createOrderDetail(Order order, List<OrderRequestDto> requestDtos);
}
