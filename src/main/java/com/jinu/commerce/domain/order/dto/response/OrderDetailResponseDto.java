package com.jinu.commerce.domain.order.dto.response;

import com.jinu.commerce.domain.order.entity.OrderDetail;
import com.jinu.commerce.domain.order.entity.OrderStatus;
import com.jinu.commerce.domain.product.dto.response.ProductResponseDto;
import com.jinu.commerce.domain.product.entity.Product;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long orderDetailId;

    private Long price;

    private ProductResponseDto product;

    private Long qty;


    @Builder
    public OrderDetailResponseDto(Long orderDetailId, Long price, ProductResponseDto product, Long qty) {
        this.orderDetailId = orderDetailId;
        this.price = price;
        this.product = product;
        this.qty = qty;
    }
}