package com.jinu.commerceorderservice.order.service;

import com.jinu.commerceorderservice.order.dto.request.OrderRequestDto;
import com.jinu.commerceorderservice.order.entity.Order;
import com.jinu.commerceorderservice.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void createOrderDetail(Order order, List<OrderRequestDto> requestDtos);

    List<OrderDetail> getOrderDetailsByOrder(Order order);
}
