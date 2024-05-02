package com.jinu.commerceorderservice.order.service;

import com.jinu.commerceorderservice.order.entity.Order;
import com.jinu.commerceorderservice.order.entity.Status;
import com.jinu.commerceorderservice.order.repository.OrderRepository;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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
                .status(Status.ORDER_COMPLETE).build();

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
    @Transactional(readOnly = true)
    public Order getOrderByOrderId(Long orderId) {
        log.info("orderId로 주문 조회");

        return this.repository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));
    }

    @Override
    @Transactional
    public void changeStatusToCancel(Long orderId) {
        log.info("주문취소");

        Order order = this.getOrderByOrderId(orderId);

        if (order.getStatus() == Status.ORDER_COMPLETE) {
            order.updateStatus(Status.CANCEL);
            return;
        }

        throw new CustomException(ErrorCode.CAN_NOT_CANCEL);
    }

    @Override
    @Transactional
    public void changeStatusToReturn(Long orderId) {
        log.info("주문취소");

        Order order = this.getOrderByOrderId(orderId);

        if (order.getStatus() == Status.IN_DELIVERY || order.getStatus() == Status.DELIVERED) {
            order.updateStatus(Status.RETURN);
            return;
        }

        throw new CustomException(ErrorCode.CAN_NOT_RETURN);
    }

    @Override
    @Transactional
    @Scheduled(cron = "@daily")
    public void updateOrderStatus() {
        log.info("주문 상태 업데이트");

        List<Order> orders = this.repository.findAll();

        for (Order order : orders) {
            switch (order.getStatus()) {
                case ORDER_COMPLETE -> order.updateStatus(Status.IN_DELIVERY);
                case IN_DELIVERY -> order.updateStatus(Status.DELIVERED);
            }
        }
    }
}