package com.jinu.commerceorderservice.domain.cart.service;

import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import com.jinu.commerceorderservice.domain.cart.repository.CartRepository;
import com.jinu.commerceorderservice.global.cookie.CookieUtil;
import com.jinu.commerceorderservice.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "CartServiceImpl")
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repository;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    @Transactional
    public Cart createCart(Long userId) {
        log.info("장바구니 생성");

        Cart cart = Cart.builder()
                .userId(userId)
                .build();

        this.repository.save(cart);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(HttpServletRequest req) {
        log.info("장바구니 가져오기");

        String accessToken = this.cookieUtil.getAccessTokenFromCookie(req);
        String subStringToken = this.jwtUtil.substringToken(accessToken);

        Long userId = this.jwtUtil.getUserIdFromToken(subStringToken);

        return this.repository.findByUserId(userId)
                .orElseGet(() -> this.createCart(userId));
    }
}