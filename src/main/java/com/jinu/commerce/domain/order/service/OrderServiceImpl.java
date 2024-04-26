package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.response.OrderResponseDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import com.jinu.commerce.domain.order.repository.OrderRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
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
    private final OrderRepository orderRepository;
    private final ResponseBodyDto responseBodyDto;

    @Override
    @Transactional
    public Order createOrder(UserDetailsImpl userDetails) {
        log.info("주문생성");

        Order order = Order.builder()
                .user(userDetails.getUser())
                .status(OrderStatus.ORDER_COMPLETED)
                .build();

        this.orderRepository.save(order);

        return order;
    }


    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders(UserDetailsImpl userDetails) {
        log.info("주문 전체조회");

        List<Order> orders = this.orderRepository.findAllByUser(userDetails.getUser());

        return OrderResponseDto.crateOrdersIntoResponseDtos(orders);
    }

    /*@Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getOrderDetailByOrderId(Long orderId) {
        log.info("주문 상세조회");

        Order order = this.findById(orderId);

        List<OrderDetailResponseDto> orderDetails = order.getOrderDetails().stream()
                .map(orderDetail -> OrderDetailResponseDto.builder()
                        .orderDetailId(orderDetail.getOrderDetailId())
                        .product(ProductResponseDto.builder()
                                .productId(orderDetail.getProduct().getProductId())
                                .title(orderDetail.getProduct().getTitle())
                                .price(orderDetail.getProduct().getPrice())
                                .stock(orderDetail.getProduct().getStock())
                                .build())
                        .qty(orderDetail.getQty())
                        .build())
                .toList();

        OrderResponseDto responseDto = OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .price(order.getPrice())
                .status(order.getStatus())
                .orderDetails(orderDetails)
                .build();

        return ResponseEntity.ok(responseBodyDto.successWithResult("상세조회 완료", responseDto));
    }

    @Override
    @Transactional
    public Order findById(Long orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ORDER)
        );
    }*/
}