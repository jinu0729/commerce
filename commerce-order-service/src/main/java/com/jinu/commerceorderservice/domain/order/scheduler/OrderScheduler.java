package com.jinu.commerceorderservice.domain.order.scheduler;

import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.OrderDetail;
import com.jinu.commerceorderservice.domain.order.service.OrderDetailService;
import com.jinu.commerceorderservice.domain.order.service.OrderService;
import com.jinu.commerceorderservice.global.client.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderScheduler {
    private final OrderService service;
    private final OrderDetailService detailService;
    private final ProductServiceClient productServiceClient;


    @Scheduled(cron = "@daily")
    @Transactional
    public void updateOrderStatusByDaily() {
        this.service.updateOrderStatusByDaily();
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void checkPaymentStatus() {
        List<Order> orderList = this.service.cancelOrderByTenMinutesAgo();

        for (Order order : orderList) {
            List<OrderDetail> orderDetailList = this.detailService.getOrderDetailsByOrder(order);
            for (OrderDetail orderDetail : orderDetailList) {
                productServiceClient.increaseStock(orderDetail.getProductId());
            }
        }
    }
}