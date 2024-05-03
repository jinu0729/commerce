package com.jinu.commerceorderservice.cart.service;

import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceuserservice.security.UserDetailsImpl;
import com.jinu.commerceuserservice.user.entity.User;

public interface CartService {
    Cart createCart(User user);

    Cart getCart(UserDetailsImpl userDetails);
}
