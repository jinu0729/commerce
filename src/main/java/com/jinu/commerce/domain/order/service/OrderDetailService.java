package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.dto.response.OrderDetailResponseDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void createOrderDetail(Order order, List<OrderRequestDto> requestDtos);

    List<OrderDetail> getOrderDetailsByOrder(Order order);
}
