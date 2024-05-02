package com.jinu.commerceorderservice.cart.service;

import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.security.UserDetailsImpl;

public interface CartService {
    Cart createCart(User user);

    Cart getCart(UserDetailsImpl userDetails);
}
