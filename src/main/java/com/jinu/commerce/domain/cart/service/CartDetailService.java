package com.jinu.commerce.domain.cart.service;

import com.jinu.commerce.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerce.domain.cart.entity.Cart;

import java.util.List;

public interface CartDetailService {
    void addCartItems(Cart cart, List<CartRequestDto> requestDtos);

    // List<CartItem> getOrderDetailsByOrder(Cart cart);
}
