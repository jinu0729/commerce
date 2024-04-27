package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderDetail;
import com.jinu.commerce.domain.order.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "OrderDetailServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository repository;

    @Override
    @Transactional
    public void createOrderDetail(Order order, List<OrderRequestDto> requestDtos) {
        log.info("주문상세 생성");

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderRequestDto requestDto : requestDtos) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .product(requestDto.getProduct())
                    .qty(requestDto.getQty())
                    .build();

            orderDetails.add(orderDetail);
        }

        this.repository.saveAll(orderDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrderDetailsByOrder(Order order) {
        log.info("주문 상세조회");

        return this.repository.findAllByOrder(order);
    }
}