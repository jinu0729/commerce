package com.jinu.commerceorderservice.domain.order.service;

import com.jinu.commerceorderservice.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void createOrderDetail(Order order, List<OrderRequestDto> requestDtos);

    List<OrderDetail> getOrderDetailsByOrder(Order order);
}
