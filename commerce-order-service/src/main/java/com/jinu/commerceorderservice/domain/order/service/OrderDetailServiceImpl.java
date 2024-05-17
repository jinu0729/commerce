package com.jinu.commerceorderservice.domain.order.service;

import com.jinu.commerceorderservice.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.OrderDetail;
import com.jinu.commerceorderservice.domain.order.repository.OrderDetailRepository;
import com.jinu.commerceorderservice.global.client.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "OrderDetailServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository repository;
    private final ProductServiceClient productServiceClient;

    @Override
    @Transactional
    public void createOrderDetail(Order order, List<OrderRequestDto> requestDtos) {
        log.info("주문상세 생성");

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderRequestDto requestDto : requestDtos) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .productId(requestDto.getProductId())
                    .qty(requestDto.getQty())
                    .build();

            orderDetails.add(orderDetail);
        }

        this.repository.saveAll(orderDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrderDetailsByOrder(Order order) {

        return this.repository.findAllByOrder(order);
    }
}