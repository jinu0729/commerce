package com.jinu.commerceorderservice.cart.service;

import com.jinu.commerceorderservice.cart.dto.request.CartItemEditRequestDto;
import com.jinu.commerceorderservice.cart.dto.request.CartRequestDto;
import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceorderservice.cart.entity.CartItem;

import java.util.List;

public interface CartDetailService {
    void addCartItems(Cart cart, List<CartRequestDto> requestDtos);

    List<CartItem> getAllCartItemsByCart(Cart cart);

    void editCartItemQty(CartItemEditRequestDto cartItemId);

    CartItem getCartItemById(Long cartItemId);

    void deleteCartItemById(Long cartItemId);
}
