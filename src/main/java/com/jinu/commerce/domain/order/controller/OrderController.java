package com.jinu.commerce.domain.order.controller;

import com.jinu.commerce.domain.order.dto.OrderRequestDto;
import com.jinu.commerce.domain.order.service.OrderService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<ResponseBodyDto> registerOrder(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @RequestBody List<OrderRequestDto> requestDtos) {
        return this.orderService.registerOrder(userDetails, requestDtos);
    }
}