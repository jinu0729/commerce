package com.jinu.commerceorderservice.domain.order.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceorderservice.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerceorderservice.domain.order.dto.response.OrderDetailResponseDto;
import com.jinu.commerceorderservice.domain.order.dto.response.OrderResponseDto;
import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.OrderDetail;
import com.jinu.commerceorderservice.domain.order.service.OrderDetailService;
import com.jinu.commerceorderservice.domain.order.service.OrderService;
import com.jinu.commerceorderservice.global.client.ProductServiceClient;
import com.jinu.commerceorderservice.global.client.dto.ProductResponseDto;
import com.jinu.commerceorderservice.global.util.OrderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final ProductServiceClient productServiceClient;
    private final OrderUtil orderUtil;

    @PostMapping()
    public ResponseEntity<ResponseBodyDto> createOrder(@RequestBody List<OrderRequestDto> requestDtoList,
                                                       HttpServletRequest req) {
        Long userId = this.orderUtil.getUserIdFromToken(req);

        Order order = this.orderService.createOrder(userId);
        this.orderDetailService.createOrderDetail(order, requestDtoList);

        return ResponseEntity.ok(ResponseBodyDto.success("주문완료"));
    }

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(HttpServletRequest req) {
        Long userId = this.orderUtil.getUserIdFromToken(req);

        List<Order> orderList = this.orderService.getAllOrders(userId);
        List<OrderResponseDto> responseDtoList = OrderResponseDto.crateOrdersIntoResponseDtos(orderList);

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("전체조회 완료", responseDtoList));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseBodyDto> getOrderDetailsByOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = this.orderService.getOrderByOrderId(orderId);

        List<OrderDetail> orderDetailList = this.orderDetailService.getOrderDetailsByOrder(order);
        List<Long> productIdList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetailList) {
            productIdList.add(orderDetail.getProductId());
        }

        List<ProductResponseDto> productResponseDtoList =
                this.productServiceClient.getProductsByIdForOrderDetails(productIdList);

        List<OrderDetailResponseDto> orderDetailResponseDtoList =
                OrderDetailResponseDto.createOrderDetailList(orderDetailList, productResponseDtoList);

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("상세조회 완료", orderDetailResponseDtoList));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseBodyDto> cancelOrder(@PathVariable(name = "orderId") Long orderId) {
        this.orderService.changeStatusToCancel(orderId);

        return ResponseEntity.ok(ResponseBodyDto.success("취소완료"));
    }

    @PatchMapping("/return/{orderId}")
    public ResponseEntity<ResponseBodyDto> returnOrder(@PathVariable(name = "orderId") Long orderId) {
        this.orderService.changeStatusToReturn(orderId);

        return ResponseEntity.ok(ResponseBodyDto.success("반품완료"));
    }
}