package com.jinu.commerceorderservice.domain.order.service;

import com.jinu.commercecommon.exception.CustomException;
import com.jinu.commercecommon.exception.ErrorCode;
import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.Status;
import com.jinu.commerceorderservice.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j(topic = "OrderServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;


    @Override
    @Transactional
    public Order createOrder(Long userId) {
        log.info("주문생성");

        Order order = Order.builder()
                .userId(userId)
                .status(Status.PAYING).build();

        this.repository.save(order);

        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders(Long userId) {
        log.info("주문 전체조회");

        return this.repository.findAllByUserId(userId);
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
    public Order cancelOrder(Long orderId) {
        log.info("해당주문 취소");

        Order order = this.getOrderByOrderId(orderId);

        if (order.getStatus() != Status.PAID) {
            throw new CustomException(ErrorCode.CAN_NOT_CANCEL);
        }

        order.updateStatus(Status.CANCELED);
        return order;
    }

    @Override
    @Transactional
    public Order returnOrder(Long orderId) {
        log.info("해당주문 반품");

        Order order = this.getOrderByOrderId(orderId);

        if (order.getStatus() == Status.IN_DELIVERY || order.getStatus() == Status.DELIVERED) {
            order.updateStatus(Status.RETURNED);
            return order;
        }

        throw new CustomException(ErrorCode.CAN_NOT_RETURN);
    }

    @Override
    @Transactional
    public void updateOrderStatusByDaily() {
        log.info("배송 상태 업데이트");

        List<Order> orders = this.repository.findAll();

        for (Order order : orders) {
            switch (order.getStatus()) {
                case PAID -> order.updateStatus(Status.IN_DELIVERY);
                case IN_DELIVERY -> order.updateStatus(Status.DELIVERED);
            }
        }
    }

    @Override
    @Transactional
    public List<Order> cancelOrderByTenMinutesAgo() {
        log.info("주문 후 10분 내 미결제 건 취소처리");

        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);

        List<Order> orderList =
                this.repository.findOrdersByStatusAndCreatedAtBeforeGivenTime(Status.PAYING, tenMinutesAgo);

        for (Order order : orderList) {
            order.updateStatus(Status.CANCELED);
        }

        return orderList;
    }
}