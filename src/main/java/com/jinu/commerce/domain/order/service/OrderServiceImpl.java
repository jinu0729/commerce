package com.jinu.commerce.domain.order.service;

import com.jinu.commerce.domain.order.dto.OrderRequestDto;
import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderDetail;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import com.jinu.commerce.domain.order.repository.OrderRepository;
import com.jinu.commerce.domain.product.entity.Product;
import com.jinu.commerce.domain.product.service.ProductService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "OrderServiceImpl")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ResponseBodyDto responseBodyDto;

    @Override
    public ResponseEntity<ResponseBodyDto> registerOrder(UserDetailsImpl userDetails,
                                                         List<OrderRequestDto> orderRequestDtos) {
        log.info("상품주문");

        // 주문 생성
        Order order = Order.builder()
                .user(userDetails.getUser())
                .status(OrderStatus.ORDER_COMPLETED)
                .build();

        // 주문 상세 생성
        List<OrderDetail> orderDetails = orderRequestDtos.stream()
                .map(orderRequestDto -> {
                    Product product = this.productService.findById(orderRequestDto.getProductId());

                    return OrderDetail.builder()
                            .order(order)
                            .product(product)
                            .qty(orderRequestDto.getQty())
                            .build();
                })
                .collect(Collectors.toList());

        // 주문 가격 계산
        long price = orderDetails.stream()
                .mapToLong(orderDetail -> orderDetail.getProduct().getProductDetail().getPrice() * orderDetail.getQty())
                .sum();

        // 주문에 주문 상세 및 총 가격 설정
        order.setOrderDetails(orderDetails);
        order.setPrice(price);

        // 주문 저장
        orderRepository.save(order);

        return ResponseEntity.ok(responseBodyDto.success("주문완료"));
    }
}
