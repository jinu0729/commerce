package com.jinu.commerce.domain.cart.service;

import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.security.UserDetailsImpl;

public interface CartService {
    Cart createCart(User user);

    Cart getCart(UserDetailsImpl userDetails);
}
