package com.jinu.commerce.domain.cart.dto.request;

import lombok.Getter;

@Getter
public class CartItemEditRequestDto {
    private Long cartItemId;
    private Long qty;
}