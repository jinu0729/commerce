package com.jinu.commerceorderservice.order.controller;

import com.jinu.commerceorderservice.order.dto.request.OrderRequestDto;
import com.jinu.commerceorderservice.order.dto.response.OrderDetailResponseDto;
import com.jinu.commerceorderservice.order.dto.response.OrderResponseDto;
import com.jinu.commerceorderservice.order.entity.Order;
import com.jinu.commerceorderservice.order.entity.OrderDetail;
import com.jinu.commerceorderservice.order.service.OrderDetailService;
import com.jinu.commerceorderservice.order.service.OrderService;
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

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Order> orders = this.orderService.getAllOrders(userDetails);
        List<OrderResponseDto> responseDtos = OrderResponseDto.crateOrdersIntoResponseDtos(orders);

        return ResponseEntity.ok(responseBodyDto.successWithResult("전체조회 완료", responseDtos));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseBodyDto> getOrderDetailsByOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = this.orderService.getOrderByOrderId(orderId);

        List<OrderDetail> orderDetails = this.orderDetailService.getOrderDetailsByOrder(order);
        List<OrderDetailResponseDto> responseDtos =
                OrderDetailResponseDto.createOrderDetailsIntoOrderDetailResponseDtos(orderDetails);

        return ResponseEntity.ok(responseBodyDto.successWithResult("상세조회 완료", responseDtos));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseBodyDto> cancelOrder(@PathVariable(name = "orderId") Long orderId) {
        this.orderService.changeStatusToCancel(orderId);

        return ResponseEntity.ok(responseBodyDto.success("취소완료"));
    }

    @PatchMapping("/return/{orderId}")
    public ResponseEntity<ResponseBodyDto> returnOrder(@PathVariable(name = "orderId") Long orderId) {
        this.orderService.changeStatusToReturn(orderId);

        return ResponseEntity.ok(responseBodyDto.success("반품완료"));
    }
}