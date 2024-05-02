package com.jinu.commerceorderservice.cart.dto.request;

import lombok.Getter;

@Getter
public class CartItemEditRequestDto {
    private Long cartItemId;
    private Long qty;
}