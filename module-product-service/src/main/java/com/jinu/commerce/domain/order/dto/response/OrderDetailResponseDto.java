package com.jinu.commerce.domain.order.dto.response;

import com.jinu.commerce.domain.order.entity.OrderDetail;
import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long orderDetailId;

    private Product product;

    private Long qty;


    @Builder
    public OrderDetailResponseDto(Long orderDetailId, Product product, Long qty) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.qty = qty;
    }

    public static List<OrderDetailResponseDto> createOrderDetailsIntoOrderDetailResponseDtos(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(orderDetail -> OrderDetailResponseDto.builder()
                        .orderDetailId(orderDetail.getOrderDetailId())
                        .product(orderDetail.getProduct())
                        .qty(orderDetail.getQty())
                        .build())
                .toList();
    }
}