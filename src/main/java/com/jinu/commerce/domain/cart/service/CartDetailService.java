package com.jinu.commerce.domain.cart.service;

import com.jinu.commerce.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.cart.entity.CartItem;

import java.util.List;

public interface CartDetailService {
    void addCartItems(Cart cart, List<CartRequestDto> requestDtos);

    List<CartItem> getAllCartItemsByCart(Cart cart);
}
