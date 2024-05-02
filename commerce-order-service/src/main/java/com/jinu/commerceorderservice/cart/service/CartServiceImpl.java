package com.jinu.commerceorderservice.cart.service;

import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceorderservice.cart.repository.CartRepository;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "CartServiceImpl")
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repository;

    @Override
    @Transactional
    public Cart createCart(User user) {
        log.info("장바구니 생성");

        Cart cart = Cart.builder()
                .user(user)
                .build();

        this.repository.save(cart);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(UserDetailsImpl userDetails) {
        log.info("장바구니 가져오기");

        User user = userDetails.getUser();

        return this.repository.findByUser(user)
                .orElseGet(() -> this.createCart(user));
    }
}