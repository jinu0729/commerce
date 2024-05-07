package com.jinu.commerceorderservice.domain.cart.service;

import com.jinu.commerceorderservice.domain.cart.dto.request.CartItemEditRequestDto;
import com.jinu.commerceorderservice.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import com.jinu.commerceorderservice.domain.cart.entity.CartItem;

import java.util.List;

public interface CartDetailService {
    void addCartItems(Cart cart, List<CartRequestDto> requestDtoList);

    List<CartItem> getAllCartItemsByCart(Cart cart);

    void editCartItemQty(CartItemEditRequestDto cartItemId);

    CartItem getCartItemById(Long cartItemId);

    void deleteCartItemById(Long cartItemId);
}
