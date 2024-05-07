package com.jinu.commerceorderservice.domain.order.dto.response;

import com.jinu.commerceorderservice.domain.order.entity.OrderDetail;
import com.jinu.commerceorderservice.global.client.dto.ProductResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long orderDetailId;
    private ProductResponseDto product;
    private Long qty;


    @Builder
    public OrderDetailResponseDto(Long orderDetailId, ProductResponseDto product, Long qty) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.qty = qty;
    }

    public static List<OrderDetailResponseDto> createOrderDetailList(List<OrderDetail> detailList,
                                                                     List<ProductResponseDto> productResponseDtoList) {
        return IntStream.range(0, detailList.size())
                .mapToObj(i -> OrderDetailResponseDto.builder()
                        .orderDetailId(detailList.get(i).getOrderDetailId())
                        .product(productResponseDtoList.get(i))
                        .qty(detailList.get(i).getQty())
                        .build())
                .collect(Collectors.toList());
    }
}