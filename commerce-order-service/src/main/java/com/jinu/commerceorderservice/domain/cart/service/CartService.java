package com.jinu.commerceorderservice.domain.cart.service;

import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import jakarta.servlet.http.HttpServletRequest;

public interface CartService {
    Cart createCart(Long userId);

    Cart getCart(HttpServletRequest req);
}
