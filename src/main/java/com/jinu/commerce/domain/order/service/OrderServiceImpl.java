package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.request.OrderRequestDto;
import com.jinu.commerce.domain.order.dto.response.OrderDetailResponseDto;
import com.jinu.commerce.domain.order.dto.response.OrderResponseDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderDetail;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import com.jinu.commerce.domain.order.repository.OrderDetailRepository;
import com.jinu.commerce.domain.order.repository.OrderRepository;
import com.jinu.commerce.domain.product.dto.response.ProductResponseDto;
import com.jinu.commerce.domain.product.entity.Product;
import com.jinu.commerce.domain.product.service.ProductService;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "OrderServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ResponseBodyDto responseBodyDto;

    @Override
    @Transactional
    public ResponseEntity<ResponseBodyDto> registerOrder(UserDetailsImpl userDetails,
                                                         List<OrderRequestDto> orderRequestDtos) {
        log.info("상품주문");

        // 주문 생성
        Order order = Order.builder()
                .user(userDetails.getUser())
                .status(OrderStatus.ORDER_COMPLETED)
                .build();

        // 주문 상세 및 가격 계산
        List<OrderDetail> orderDetails = new ArrayList<>();

        long price = 0;

        for (OrderRequestDto orderRequestDto : orderRequestDtos) {
            Product product = this.productService.findById(orderRequestDto.getProductId());

            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .qty(orderRequestDto.getQty())
                    .build();

            orderDetails.add(orderDetail);

            price += product.getPrice() * orderRequestDto.getQty();
        }

        // order 주문 상세 및 총 가격 설정
        order.setOrderDetails(orderDetails);
        order.setPrice(price);

        // 주문 및 주문 상세 저장
        this.orderRepository.save(order);
        this.orderDetailRepository.saveAll(orderDetails);

        return ResponseEntity.ok(responseBodyDto.success("주문완료"));
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getAllOrders(UserDetailsImpl userDetails) {
        log.info("주문 전체조회");

        User user = userDetails.getUser();

        List<OrderResponseDto> responseDtos = this.orderRepository.findAllByUser(user).stream()
                .map(order -> OrderResponseDto.builder()
                        .orderId(order.getOrderId())
                        .price(order.getPrice())
                        .status(order.getStatus())
                        .build())
                .toList();

        return ResponseEntity.ok(responseBodyDto.successWithResult("전체조회 완료", responseDtos));
    }

    @Override
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
    }
}