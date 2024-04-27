package com.jinu.commerce.global.config;

import com.jinu.commerce.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {
    private final OrderService orderService;

    void updateOrderStatus() {

    }
}
