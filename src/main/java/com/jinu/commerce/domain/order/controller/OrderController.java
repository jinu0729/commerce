package com.jinu.commerce.domain.order.controller;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.service.OrderDetailService;
import com.jinu.commerce.domain.order.service.OrderService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final ResponseBodyDto responseBodyDto;

    @PostMapping()
    public ResponseEntity<ResponseBodyDto> createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody List<OrderRequestDto> requestDtos) {
        Order order = this.orderService.createOrder(userDetails);
        this.orderDetailService.createOrderDetail(order, requestDtos);

        return ResponseEntity.ok(responseBodyDto.success("주문완료"));
    }

    /*@GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return this.orderService.getAllOrders(userDetails);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseBodyDto> getOrderDetailByOrderId(@PathVariable(name = "orderId") Long orderId) {
        return this.orderService.getOrderDetailByOrderId(orderId);
    }*/
}