package com.jinu.commerceorderservice.domain.cart.repository;

import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import com.jinu.commerceorderservice.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);
}