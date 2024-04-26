package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import com.jinu.commerce.domain.order.repository.OrderRepository;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j(topic = "OrderServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    @Transactional
    public Order createOrder(UserDetailsImpl userDetails) {
        log.info("주문생성");

        Order order = Order.builder()
                .user(userDetails.getUser())
                .status(OrderStatus.ORDER_COMPLETED)
                .build();

        this.repository.save(order);

        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders(UserDetailsImpl userDetails) {
        log.info("주문 전체조회");

        return this.repository.findAllByUser(userDetails.getUser());
    }

    @Override
    @Transactional
    public Order getOrderByOrderId(Long orderId) {
        log.info("orderId로 주문 조회");

        return this.repository.findById(orderId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ORDER)
        );
    }
}